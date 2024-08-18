package com.elleined.security_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping
    public String get() {
        return "THIS SHOULD ONLY BE ACCESSIBLE WITH ADMIN ROLE AND CANNOT BE ACCESS WITH USER ROLE";
    }
}
