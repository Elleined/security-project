package com.elleined.security_project.controller;

import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.RoleRepository;
import com.elleined.security_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public Role getById(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role with id of "));
    }

    @GetMapping
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @PatchMapping("/{id}")
    public void addRole(@PathVariable("id") int id,
                        @RequestParam("userId") int userId) {

        User user = userRepository.findById(userId).orElseThrow();
        Role role = roleRepository.findById(id).orElseThrow();
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @PatchMapping("/{id}")
    public void removeRole(@PathVariable("id") int id,
                           @RequestParam("userId") int userId) {

        User user = userRepository.findById(userId).orElseThrow();
        Role role = roleRepository.findById(id).orElseThrow();
        user.getRoles().remove(role);

        userRepository.save(user);
    }
}
