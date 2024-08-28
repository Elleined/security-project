package com.elleined.security_project.controller;

import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.PermissionRepository;
import com.elleined.security_project.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionRepository permissionRepository;

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public Permission getById(@PathVariable("id") int id) {
        return permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission with id of "));
    }

    @GetMapping
    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }

    @PatchMapping("/{id}")
    public void addPermission(@PathVariable("id") int id,
                              @RequestParam("userId") int userId) {

        User user = userRepository.findById(userId).orElseThrow();
        Permission permission = permissionRepository.findById(id).orElseThrow();

        user.getPermissions().add(permission);
        userRepository.save(user);
    }

    @PatchMapping("/{id}")
    public void removePermission(@PathVariable("id") int id,
                                 @RequestParam("userId") int userId) {

        User user = userRepository.findById(userId).orElseThrow();
        Permission permission = permissionRepository.findById(id).orElseThrow();

        user.getPermissions().remove(permission);
        userRepository.save(user);
    }
}
