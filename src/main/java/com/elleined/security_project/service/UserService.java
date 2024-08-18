package com.elleined.security_project.service;

import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.UserRepository;
import com.elleined.security_project.request.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    public Authentication authenticate(String email,
                                       String password) {

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    public User register(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();

        userRepository.save(user);
        log.debug("Saving user success");
        return user;
    }
}
