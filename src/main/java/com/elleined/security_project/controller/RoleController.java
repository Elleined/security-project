package com.elleined.security_project.controller;

import com.elleined.security_project.jwt.JWTService;
import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import com.elleined.security_project.service.UserService;
import com.elleined.security_project.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;

    private final JWTService jwtService;

    @GetMapping("/roles")
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/roles/{id}")
    public Role getById(@PathVariable("id") int id) {
        return roleService.getById(id);
    }

    @PostMapping("/users/roles/{id}")
    public void addRole(@PathVariable("id") int id,
                        @RequestHeader("Authorization") String jwt) {

        String email = jwtService.getEmail(jwt);

        User user = userService.getByEmail(email);
        Role role = roleService.getById(id);

        roleService.addRole(user, role);
    }

    @DeleteMapping("/users/roles/{id}")
    public void removeRole(@PathVariable("id") int id,
                           @RequestHeader("Authorization") String jwt) {

        String email = jwtService.getEmail(jwt);

        User user = userService.getByEmail(email);
        Role role = roleService.getById(id);

        roleService.removeRole(user, role);
    }
}
