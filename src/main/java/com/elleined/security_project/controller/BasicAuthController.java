package com.elleined.security_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BasicAuthController {

    @GetMapping
    public String basicAuth() {
        return "Hello this endpoint should be secured";
    }
}
