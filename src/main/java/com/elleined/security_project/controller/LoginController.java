package com.elleined.security_project.controller;

import com.elleined.security_project.service.JWTService;
import com.elleined.security_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final JWTService jwtService;

    @PostMapping
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password) {

        Authentication authentication = userService.authenticate(email, password);

        if (!authentication.isAuthenticated())
            throw new UsernameNotFoundException("Invalid credential");

        return jwtService.generateToken(email);
    }
}
