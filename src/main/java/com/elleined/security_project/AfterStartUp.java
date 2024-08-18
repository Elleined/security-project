package com.elleined.security_project;

import com.elleined.security_project.model.User;
import com.elleined.security_project.populator.Populator;
import com.elleined.security_project.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AfterStartUp {
    private final PasswordEncoder passwordEncoder;
    private final Faker faker;

    private final UserRepository userRepository;

    @PostConstruct
    void init() {
        if (userRepository.existsById(1))
            return;

        Populator userPopulator = () -> userRepository.saveAll(List.of(
                User.builder()
                        .name(faker.name().fullName())
                        .email(faker.bothify("??##@gmail.com"))
                        .password(passwordEncoder.encode("admin"))
                        .roles(List.of("ADMIN"))
                        .build(),

                User.builder()
                        .name(faker.name().fullName())
                        .email(faker.bothify("??##@gmail.com"))
                        .password(passwordEncoder.encode("user"))
                        .roles(List.of("USER"))
                        .build()
        ));

        log.debug("Please wait saving default values...");
        userPopulator.populate();
        log.debug("Saving default values success...");
    }
}
