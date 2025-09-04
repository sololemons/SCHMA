package com.studentservice.security.authentication.services;

import com.studentservice.security.configuration.JwtService;
import com.studentservice.security.dtos.AuthenticationRequest;
import com.studentservice.security.dtos.AuthenticationResponse;
import com.studentservice.security.dtos.RegisterRequest;
import com.studentservice.student.entities.Student;
import com.studentservice.student.exceptions.MissingFieldException;
import com.studentservice.student.exceptions.UserNotFoundException;
import com.studentservice.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final PasswordEncoder passwordEncoder;
    private final StudentRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        String email = request.getEmail();
        String password = request.getPassword().trim();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password

                )
        );
        var user = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        String role = user.getRole().toString();
        int form = user.getClassName();
        String stream = user.getStream();
        String formName = form + stream;
        var jwtToken = jwtService.generateAuthenticationToken(user);



        return AuthenticationResponse.builder().
                token(jwtToken)
                .role(role)
                .form(formName)
                .build();
    }
    public AuthenticationResponse register(RegisterRequest request) {
        String email = request.getEmail();
        String password = request.getPassword().trim();
        String confirmPassword = request.getConfirmPassword().trim();

        if (!password.equals(confirmPassword)) {
            throw new MissingFieldException("Passwords do not match!");
        }

        Student existingStudent = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Email is not registered!"));

        if (existingStudent.getPassword() != null && !existingStudent.getPassword().isBlank()) {
            throw new MissingFieldException("Password is already set for this account.");
        }

        existingStudent.setPassword(passwordEncoder.encode(password));
        repository.save(existingStudent);

        String jwtToken = jwtService.generateRegistrationToken(existingStudent);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}