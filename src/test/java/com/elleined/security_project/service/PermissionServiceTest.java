package com.elleined.security_project.service;

import static org.junit.jupiter.api.Assertions.*;

import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.User;
import com.elleined.security_project.service.permission.PermissionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    @Test
    void hasPermission() {
        // Pre defined values

        // Expected Value

        // Mock data
        Permission permission = new Permission();

        User user = User.builder()
                .permissions(Set.of(permission))
                .build();

        // Set up method

        // Stubbing methods

        // Calling the method
        boolean hasPermission = PermissionService.hasPermission(user, permission);
        assertTrue(hasPermission);

        // Behavior Verifications

        // Assertions
    }

    @Test
    void hasAnyPermission() {
        // Pre defined values

        // Expected Value

        // Mock data
        Permission permission1 = new Permission();
        Permission permission2 = new Permission();

        User user = User.builder()
                .permissions(Set.of(permission1, permission2))
                .build();

        // Set up method

        // Stubbing methods

        // Calling the method
        boolean hasAnyPermission = PermissionService.hasAnyPermission(user, permission1, permission2);
        assertTrue(hasAnyPermission);

        // Behavior Verifications

        // Assertions
    }

    @Test
    void testHasAnyPermission() {
        // Pre defined values

        // Expected Value

        // Mock data
        Permission permission1 = new Permission();
        Permission permission2 = new Permission();

        User user = User.builder()
                .permissions(Set.of(permission1, permission2))
                .build();

        // Set up method
        Set<Permission> otherPermissions = Set.of(permission1, permission2, new Permission(), new Permission());

        // Stubbing methods

        // Calling the method
        boolean hasAnyPermission = PermissionService.hasAnyPermission(user, otherPermissions);
        assertTrue(hasAnyPermission);

        // Behavior Verifications

        // Assertions
    }
}