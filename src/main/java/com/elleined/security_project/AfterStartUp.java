package com.elleined.security_project;

import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import com.elleined.security_project.populator.Populator;
import com.elleined.security_project.repository.PermissionRepository;
import com.elleined.security_project.repository.RoleRepository;
import com.elleined.security_project.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AfterStartUp {
    private final PasswordEncoder passwordEncoder;
    private final Faker faker;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @PostConstruct
    void init() {
        if (userRepository.existsById(1))
            return;


        Populator rolePopulator = () -> roleRepository.saveAll(List.of(
                Role.builder()
                        .name("ADMIN")
                        .build(),

                Role.builder()
                        .name("USER")
                        .build()
        ));

        Populator permissionPopulator = () -> permissionRepository.saveAll(List.of(
                Permission.builder()
                        .name("RESOURCE:CREATE")
                        .build(),

                Permission.builder()
                        .name("RESOURCE:DELETE")
                        .build()
        ));

        Populator userPopulator = () -> userRepository.saveAll(List.of(
                User.builder()
                        .name(faker.name().fullName())
                        .email("cAdmin")
                        .password(passwordEncoder.encode("cAdmin"))
                        .roles(Set.of(roleRepository.findById(1).orElseThrow(), roleRepository.findById(2).orElseThrow()))
                        .permissions(Set.of(permissionRepository.findById(1).orElseThrow()))
                        .build(),

                User.builder()
                        .name(faker.name().fullName())
                        .email("dAdmin")
                        .password(passwordEncoder.encode("dAdmin"))
                        .roles(Set.of(roleRepository.findById(1).orElseThrow(), roleRepository.findById(2).orElseThrow()))
                        .permissions(Set.of(permissionRepository.findById(2).orElseThrow()))
                        .build(),

                User.builder()
                        .name(faker.name().fullName())
                        .email("user")
                        .password(passwordEncoder.encode("user"))
                        .roles(Set.of(roleRepository.findById(2).orElseThrow()))
                        .permissions(Set.of(permissionRepository.findById(2).orElseThrow()))
                        .build()
        ));

        log.debug("Please wait saving default values...");
        rolePopulator.populate();
        permissionPopulator.populate();
        userPopulator.populate();
        log.debug("Saving default values success...");
    }
}
