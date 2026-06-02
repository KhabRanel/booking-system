package com.example.booking.service;

import com.example.booking.domain.entity.User;
import com.example.booking.domain.enums.Role;
import com.example.booking.domain.repository.UserRepository;
import com.example.booking.exception.EmailAlreadyExistsException;
import com.example.booking.security.JwtService;
import com.example.booking.security.UserDetailsServiceImpl;
import com.example.booking.web.dto.auth.AuthRequest;
import com.example.booking.web.dto.auth.AuthResponse;
import com.example.booking.web.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .fullName(request.fullName())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        return new AuthResponse(jwtService.generateToken(userDetails));
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());

        return new AuthResponse(jwtService.generateToken(userDetails));
    }
}
