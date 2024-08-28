package com.elleined.security_project.service;

import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public interface AccessService {
    // Role
    static boolean hasRole(User user, Role role) {
        return user.getRoles().contains(role);
    }

    static boolean hasAnyRole(User user, Role... roles) {
        return Stream.of(roles).anyMatch(user.getRoles()::contains);
    }

    static boolean hasAnyRole(User user, Collection<Role> roles) {
        return user.getRoles().stream()
                .anyMatch(roles::contains);
    }

    // Permission
    static boolean hasPermission(User user, Permission permission) {
        return user.getPermissions().contains(permission);
    }

    static boolean hasAnyPermission(User user, Permission... permissions) {
        return Stream.of(permissions).anyMatch(user.getPermissions()::contains);
    }

    static boolean hasAnyPermission(User user, Collection<Permission> permissions) {
        return user.getPermissions().stream()
                .anyMatch(permissions::contains);
    }

}
