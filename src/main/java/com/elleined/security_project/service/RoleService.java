package com.elleined.security_project.service;

import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.RoleRepository;
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
public class RoleService {
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public Role getById(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role with id of "));
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public void addRole(User user, Role role) {
        user.getRoles().add(role);

        userRepository.save(user);
    }

    public void removeRole(User user, Role role) {
        user.getRoles().remove(role);

        userRepository.save(user);
    }
}
