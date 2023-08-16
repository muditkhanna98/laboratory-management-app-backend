package com.humber.laboratorymgntappbackend.services;

import com.humber.laboratorymgntappbackend.exceptions.AuthenticationFailedException;
import com.humber.laboratorymgntappbackend.exceptions.UserAlreadyExistsException;
import com.humber.laboratorymgntappbackend.config.JwtService;
import com.humber.laboratorymgntappbackend.exceptions.UserNotFoundException;
import com.humber.laboratorymgntappbackend.jsons.AuthenticationRequest;
import com.humber.laboratorymgntappbackend.jsons.AuthenticationResponse;
import com.humber.laboratorymgntappbackend.jsons.RegisterRequest;
import com.humber.laboratorymgntappbackend.models.Role;
import com.humber.laboratorymgntappbackend.models.User;
import com.humber.laboratorymgntappbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User
                .builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().equals(Role.admin.name()) ? Role.admin
                        : (request.getRole().equals(Role.staff.name()) ? Role.staff : (request.getRole().equals(Role.physician.name()) ? Role.physician : Role.technician)))
                .build();
        Optional<User> result = this.userRepository.findByUsername((request.getUsername()));
        System.out.println(result);
        if (result.isPresent()) {
            throw new UserAlreadyExistsException("Account with username:" + request.getUsername() + " already exists");
        }
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        Optional<User> user = this.userRepository.findByUsername(request.getUsername());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with username does not exist");
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername()
                            , request.getPassword()));
        } catch (Exception e) {
            throw new AuthenticationFailedException("Wrong email or password");
        }
        String jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.get().getUsername())
                .role(user.get().getRole().name())
                .build();
    }
}
