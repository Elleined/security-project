package com.elleined.security_project.controller;

import com.elleined.security_project.jwt.JWTService;
import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rbac") // Role Based Access Control
@RequiredArgsConstructor
public class RBACController {
    private final UserRepository userRepository;
    private final JWTService jwtService;

    @GetMapping("/admin")
    public String adminRole() {
        return "ADMIN ROLE";
    }

    @PostMapping("/admin/create")
    public String adminRoleWithCreatePermission(@RequestHeader("Authorization") String jwt) {
        String email = jwtService.getEmail(jwt);
        User user = userRepository.findByEmail(email).orElseThrow();

        return "ADMIN ROLE WITH CREATE PERMISSION";
    }

    @DeleteMapping("/admin/delete")
    public String adminRoleWithDeletePermission(@RequestHeader("Authorization") String jwt) {
        String email = jwtService.getEmail(jwt);
        User user = userRepository.findByEmail(email).orElseThrow();

        return "ADMIN ROLE WITH DELETE PERMISSION";
    }

    @GetMapping("/user")
    public String user() {
        return "USER ROLE";
    }
}
