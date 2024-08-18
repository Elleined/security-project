package com.elleined.security_project.controller;

import com.elleined.security_project.model.User;
import com.elleined.security_project.request.UserRequest;
import com.elleined.security_project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @PostMapping
    public String register(@Valid @RequestBody UserRequest request) {
        User user = userService.register(request);
        return user.getName();
    }
}
