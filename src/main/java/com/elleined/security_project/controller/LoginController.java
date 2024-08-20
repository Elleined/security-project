package com.elleined.security_project.controller;

import com.elleined.security_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping
    @ResponseBody
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password) {

        return userService.authenticate(email, password);
    }
}
