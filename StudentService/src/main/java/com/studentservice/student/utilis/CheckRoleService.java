package com.studentservice.student.utilis;

import com.shared.dtos.RequestRoleDto;
import  com.shared.dtos.RoleDto;
import com.studentservice.student.configuration.RabbitMQConfiguration;
import com.studentservice.student.entities.Student;
import com.studentservice.student.exceptions.MissingFieldException;
import com.studentservice.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckRoleService {
    private final StudentRepository studentRepository;
    private final RabbitTemplate rabbitTemplate;

    public RoleDto checkRoleForUser(RequestRoleDto requestRoleDto) {
        String email = requestRoleDto.getEmail();

        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            RoleDto roleDto = new RoleDto();
            roleDto.setEmail(email);
            roleDto.setRole("STUDENT");
            return roleDto;
        }

        Object response = rabbitTemplate.convertSendAndReceive(
                RabbitMQConfiguration.CHECK_ROLE_EXCHANGE,
                "",
                requestRoleDto
        );

        if (!(response instanceof RoleDto)) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {}

            response = rabbitTemplate.convertSendAndReceive(
                    RabbitMQConfiguration.CHECK_ROLE_EXCHANGE,
                    "",
                    requestRoleDto
            );
        }


        if (response instanceof RoleDto roleDto) {
            return roleDto;
        }

        throw new MissingFieldException("User with email " + email + " not found in any service.");
    }

}
