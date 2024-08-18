package com.elleined.security_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role-authorization")
@RequiredArgsConstructor
public class RoleController {

    @GetMapping("/admin")
    public String admin() {
        return "THIS SHOULD ONLY BE ACCESSIBLE WITH ADMIN ROLE AND CANNOT BE ACCESS WITH USER ROLE";
    }

    @GetMapping("/user")
    public String user() {
        return "THIS CAN BE ACCESSIBLE WITH ADMIN OR USER ROLE";
    }
}
