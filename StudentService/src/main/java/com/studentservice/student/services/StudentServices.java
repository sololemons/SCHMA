package com.studentservice.student.services;

import com.shared.dtos.SubmissionRequestDto;
import com.studentservice.student.configuration.RabbitMQConfiguration;
import com.studentservice.student.dtos.StudentDto;
import com.studentservice.student.entities.Role;
import com.studentservice.student.entities.Student;
import com.studentservice.student.exceptions.UserNotFoundException;
import com.studentservice.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServices {

    private final StudentRepository studentRepository;
    private final RabbitTemplate rabbitTemplate;


    public Page<StudentDto> getFilteredStudents(Long admissionId, Integer admissionYear, String stream, Pageable pageable) {
        Page<Student> students = studentRepository.findByFilters(admissionId, admissionYear, stream, pageable);
        return students.map(this::mapToDto);
    }



    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());


    }
    private StudentDto mapToDto(Student student) {
        return new StudentDto(

                student.getEmail(),
                student.getFirstName(),
                student.getLastName(),
                student.getAdmissionId(),
                student.getAdmissionYear(),
                student.getStream(),
                student.getGender()
        );
    }

    public StudentDto getStudentByAdmissionId(Long admissionId) {
        Optional<Student> studentOptional = studentRepository.findById(admissionId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return mapToDto(student);
        } else {
            throw new UserNotFoundException("Student not found with admission ID: " + admissionId);
        }
    }





    public StudentDto updateStudent(StudentDto studentDto) {
        long admissionId = studentDto.getAdmissionId();
        Optional<Student> existingStudent = studentRepository.findById(admissionId);

        if (existingStudent.isPresent()) {

            Student studentToUpdate = existingStudent.get();
            studentToUpdate.setFirstName(studentDto.getFirstName());
            studentToUpdate.setLastName(studentDto.getLastName());
            studentToUpdate.setGender(studentDto.getGender());
            studentToUpdate.setStream(studentDto.getStream());
            studentToUpdate.setAdmissionYear(studentDto.getAdmissionYear());
            studentToUpdate.setEmail(studentDto.getEmail());

            Student updatedStudent = studentRepository.save(studentToUpdate);


            return studentDto;
        } else {
            throw new UserNotFoundException("Failed to Update student with admission ID: " + admissionId);
        }
    }

    public String deleteStudent(Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            studentRepository.delete(existingStudent.get());
        }
        else {
            throw new UserNotFoundException("Student not found with id: " + id);
        }
        return "Student deleted successfully";
    }

    public String submitAssignment(SubmissionRequestDto submissionRequestDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.ADD_ASSIGNMENT_QUEUE, submissionRequestDto);
        return "Assignment submitted";
    }

    public StudentDto getActiveStudent(Principal principal) {
        String email = principal.getName();
        Optional<Student> studentOptional = studentRepository.findByEmail(email);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return mapToDto(student);
        }
        throw new UserNotFoundException("Student not found with email: " + email);
    }
}