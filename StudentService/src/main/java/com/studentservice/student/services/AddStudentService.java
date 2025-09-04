package com.studentservice.student.services;

import com.studentservice.student.configuration.RabbitMQConfiguration;
import com.shared.dtos.StudentDto;
import com.studentservice.student.entities.Role;
import com.studentservice.student.entities.Student;
import com.studentservice.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AddStudentService {
    private final StudentRepository repository;

    @RabbitListener(queues = RabbitMQConfiguration.ADD_STUDENT_QUEUE)
    public void addStudent(StudentDto student) {

        System.out.println(student);

        Student newStudent = new Student();
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setGender(student.getGender());
        newStudent.setClassName(student.getClassName());
        newStudent.setStream(student.getStream());
        newStudent.setAdmissionYear(student.getAdmissionYear());
        newStudent.setEmail(student.getEmail());
        newStudent.setAdmissionId(10277L);
        newStudent.setRole(Role.STUDENT);
        repository.save(newStudent);


    }
}
