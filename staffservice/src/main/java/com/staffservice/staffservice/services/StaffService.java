package com.staffservice.staffservice.services;

import com.shared.dtos.SubmissionRequestDto;
import com.staffservice.staffservice.configuration.RabbitMQConfiguration;
import com.staffservice.staffservice.dtos.*;
import com.staffservice.staffservice.entities.*;
import com.shared.dtos.StaffPayload;
import com.staffservice.staffservice.entities.Class;
import com.staffservice.staffservice.exceptions.UserNotFoundException;
import com.staffservice.staffservice.repositories.AssignmentRepository;
import com.staffservice.staffservice.repositories.ClassRepository;
import com.staffservice.staffservice.repositories.StaffRepository;
import com.staffservice.staffservice.repositories.SubmissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final Logger log = LoggerFactory.getLogger(StaffService.class);
    private final AssignmentRepository assignmentRepository;
    private final ClassRepository classRepository;
    private final SubmissionRepository submissionRepository;


    public List<StaffDto> getAllStaffMembers() {

        List<Staff> staffList = staffRepository.findAll();
        return staffList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Page<StaffDto> getFilteredStaff(Long staffId, Long admissionYear, String department, Pageable pageable) {
        Page<Staff> staffPage = staffRepository.findByFilters(staffId, admissionYear, department, pageable);
        return staffPage.map(this::mapToDto);
    }


    public ResponseEntity<StaffDto> getStaffByStaffId(Long staffID) {

        Optional<Staff> staffOptional = staffRepository.findById(staffID);

        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            StaffDto staffDto = mapToDto(staff);
            return ResponseEntity.ok(staffDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public String deleteStaffByID(Long staffId) {
        Optional<Staff> staffOptional = staffRepository.findById(staffId);
        if (staffOptional.isPresent()) {
            staffRepository.delete(staffOptional.get());
            return "Staff deleted successfully";
        } else {
            throw new UserNotFoundException("Staff not found with ID: " + staffId);
        }
    }

    private StaffDto mapToDto(Staff staff) {
        return new StaffDto(
                staff.getFirstName(),
                staff.getStaffId(),
                staff.getLastName(),
                staff.getEmail(),
                staff.getDepartment(),
                staff.getPosition(),
                staff.getGender(),
                staff.getPhoneNumber(),
                staff.getBirthYear(),
                staff.getAdmissionYear(),
                staff.getRole()
        );
    }

    @RabbitListener(queues = RabbitMQConfiguration.ADD_STAFF_QUEUE)
    public void addStaff(StaffPayload staffDto) {
        Staff staff = new Staff();
        staff.setRole(Role.STAFF);
        staff.setDepartment(staffDto.getDepartment());
        staff.setGender(staffDto.getGender());
        staff.setAdmissionYear(staffDto.getAdmissionYear());
        staff.setFirstName(staffDto.getFirstName());
        staff.setLastName(staffDto.getLastName());
        staff.setBirthYear(staffDto.getBirthYear());
        staff.setPhoneNumber(staffDto.getPhoneNumber());
        staff.setEmail(staffDto.getEmail());
        staff.setPosition(staffDto.getPosition());
        log.info("Staff added successfully: {}", staffDto);
        staffRepository.save(staff);

    }

    public String createAssignment(AssignmentRequest request) {
        Assignments assignment = new Assignments();
        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setCreationDate(LocalDateTime.now());

        List<Class> classes = classRepository.findByClassNameIn(request.getClasses());
        assignment.setClasses(classes);

        List<Questions> questions = request.getQuestions().stream().map(q -> {
            Questions question = new Questions();
            question.setQuestionText(q.getQuestionText());
            question.setQuestionType(QuestionType.valueOf(q.getType()));
            question.setOptions(q.getOptions());

            question.setCorrectAnswer(q.getCorrectAnswer());
            question.setAssignment(assignment);
            return question;
        }).collect(Collectors.toList());

        assignment.setQuestions(questions);

        assignmentRepository.save(assignment);
        return "Assignment created successfully";
    }

    public List<ClassDto> getAllClasses() {
        List<Class> classes = classRepository.findAll();
        return classes.stream()
                .map(d -> {
                    ClassDto dto = new ClassDto();
                    dto.setClasId(d.getClassId());
                    dto.setClassName(d.getClassName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<AssignmentDto> getAllAssignments(String className) {
        List<Assignments> assignments = assignmentRepository.findByClasses_ClassName(className);
        return assignments.stream()
                .map(AssignmentMapper::toDto)
                .toList();
    }

    @RabbitListener(queues = RabbitMQConfiguration.ADD_ASSIGNMENT_QUEUE)
    public void addAssignment(SubmissionRequestDto submissionRequestDto) {

        Submission submission = new Submission();
        submission.setClassName(submissionRequestDto.getClassName());
        submission.setSubmissionText(submissionRequestDto.getAnswerText().toString());
        submission.setSubmissionStatus(SubmissionStatus.UNGRADED);
        submission.setStudentAdmissionId(submissionRequestDto.getStudentId());
        submission.setAssignmentId(submissionRequestDto.getAssignmentId());
        submissionRepository.save(submission);
    }


    }

