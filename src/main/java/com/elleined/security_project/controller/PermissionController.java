package com.elleined.security_project.controller;

import com.elleined.security_project.jwt.JWTService;
import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.User;
import com.elleined.security_project.service.UserService;
import com.elleined.security_project.service.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;
    private final UserService userService;

    private final JWTService jwtService;

    @GetMapping("/permissions")
    public List<Permission> getAll() {
        return permissionService.getAll();
    }

    @GetMapping("/permissions/{id}")
    public Permission getById(@PathVariable("id") int id) {
        return permissionService.getById(id);
    }

    @PostMapping("/users/permissions/{id}")
    public void addPermission(@PathVariable("id") int id,
                              @RequestHeader("Authorization") String jwt) {

        String email = jwtService.getEmail(jwt);

        User user = userService.getByEmail(email);
        Permission permission = permissionService.getById(id);

        permissionService.addPermission(user, permission);
    }

    @DeleteMapping("/users/permissions/{id}")
    public void removePermission(@PathVariable("id") int id,
                                 @RequestHeader("Authorization") String jwt) {

        String email = jwtService.getEmail(jwt);

        User user = userService.getByEmail(email);
        Permission permission = permissionService.getById(id);

        permissionService.removePermission(user, permission);
    }
}
