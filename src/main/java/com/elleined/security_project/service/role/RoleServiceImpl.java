package com.elleined.security_project.service.role;

import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.RoleRepository;
import com.elleined.security_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Role getById(int id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> getAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public void addRole(User user, Role role) {
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Override
    public void removeRole(User user, Role role) {
        user.getRoles().remove(role);

        userRepository.save(user);
    }
}
