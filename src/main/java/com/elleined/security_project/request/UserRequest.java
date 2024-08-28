package com.elleined.security_project.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Please provide your name")
    private String name;

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Please provide your email")
    private String email;

    @NotBlank(message = "Please provide your password")
    private String password;

    @NotEmpty(message = "Please provide your roles")
    private Set<Integer> roleIds;

    private Set<Integer> permissionIds;
}
