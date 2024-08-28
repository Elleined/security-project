package com.elleined.security_project.controller;

import com.elleined.security_project.jwt.JWTService;
import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.UserRepository;
import com.elleined.security_project.service.resource.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rbac") // Role Based Access Control
@RequiredArgsConstructor
public class RBACController {
    private final UserRepository userRepository;
    private final JWTService jwtService;

    private final ResourceService resourceService;

    @GetMapping("/admin")
    public String adminRole() {
        return "ADMIN ROLE";
    }

    @PostMapping("/admin/create")
    public String adminRoleWithCreatePermission(@RequestHeader("Authorization") String jwt) {
        String email = jwtService.getEmail(jwt);
        User user = userRepository.findByEmail(email).orElseThrow();

        return resourceService.save(user);
    }

    @DeleteMapping("/admin/delete")
    public String adminRoleWithDeletePermission(@RequestHeader("Authorization") String jwt) {
        String email = jwtService.getEmail(jwt);
        User user = userRepository.findByEmail(email).orElseThrow();

        return resourceService.delete(user);
    }

    @GetMapping("/user")
    public String user() {
        return "USER ROLE";
    }
}
