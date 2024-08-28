package com.elleined.security_project.service;

import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.PermissionRepository;
import com.elleined.security_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    private final UserRepository userRepository;

    public Permission getById(int id) {
        return permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission with id of "));
    }

    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }

    public void addPermission(User user, Permission permission) {
        user.getPermissions().add(permission);

        userRepository.save(user);
    }

    public void removePermission(User user, Permission permission) {
        user.getPermissions().remove(permission);

        userRepository.save(user);
    }
}
