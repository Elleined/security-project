package com.elleined.security_project.service.resource;

import com.elleined.security_project.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    @Override
    public String save(User user) {
        return "ADMIN ROLE WITH CREATE PERMISSION";
    }

    @Override
    public String delete(User user) {
        return "ADMIN ROLE WITH DELETE PERMISSION";
    }
}
