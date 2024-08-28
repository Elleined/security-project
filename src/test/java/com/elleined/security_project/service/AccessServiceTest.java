package com.elleined.security_project.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class AccessServiceTest {

    @Test
    void hasRole() {
        // Pre defined values

        // Expected Value

        // Mock data
        Role role = new Role();

        User user = User.builder()
                .roles(Set.of(role))
                .build();

        // Set up method

        // Stubbing methods

        // Calling the method
        boolean hasRole = AccessService.hasRole(user, role);
        assertTrue(hasRole);

        // Behavior Verifications

        // Assertions
    }

    @Test
    void hasAnyRole() {
        // Pre defined values

        // Expected Value

        // Mock data
        Role role1 = new Role();
        Role role2 = new Role();

        User user = User.builder()
                .roles(Set.of(role1, role2))
                .build();

        // Set up method

        // Stubbing methods

        // Calling the method
        boolean hasAnyRole = AccessService.hasAnyRole(user, role1, role2);
        assertTrue(hasAnyRole);

        // Behavior Verifications

        // Assertions
    }

    @Test
    void testHasAnyRole() {
        // Pre defined values

        // Expected Value

        // Mock data
        Role role1 = new Role();
        Role role2 = new Role();

        User user = User.builder()
                .roles(Set.of(role1, role2))
                .build();

        // Set up method
        Set<Role> otherRoles = Set.of(role1, role2, new Role(), new Role());

        // Stubbing methods

        // Calling the method
        boolean hasAnyRole = AccessService.hasAnyRole(user, otherRoles);
        assertTrue(hasAnyRole);

        // Behavior Verifications

        // Assertions
    }

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
        boolean hasPermission = AccessService.hasPermission(user, permission);
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
        boolean hasAnyPermission = AccessService.hasAnyPermission(user, permission1, permission2);
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
        boolean hasAnyPermission = AccessService.hasAnyPermission(user, otherPermissions);
        assertTrue(hasAnyPermission);

        // Behavior Verifications

        // Assertions
    }
}