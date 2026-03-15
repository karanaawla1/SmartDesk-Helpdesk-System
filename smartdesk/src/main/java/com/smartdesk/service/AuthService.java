package com.smartdesk.smartdesk.service;

import com.smartdesk.smartdesk.dto.LoginRequest;
import com.smartdesk.smartdesk.dto.RegisterRequest;
import com.smartdesk.smartdesk.model.Role;
import com.smartdesk.smartdesk.model.User;
import com.smartdesk.smartdesk.repository.UserRepository;
import com.smartdesk.smartdesk.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ApplicationContext applicationContext;

    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();

        userRepository.save(user);
        return jwtService.generateToken(user.getEmail(), user.getRole().name());
    }

    public String login(LoginRequest request) {
        AuthenticationManager authenticationManager =
                applicationContext.getBean(AuthenticationManager.class);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return jwtService.generateToken(user.getEmail(), user.getRole().name());
    }
}