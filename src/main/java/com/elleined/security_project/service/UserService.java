package com.elleined.security_project.service;

import com.elleined.security_project.jwt.JWTService;
import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import com.elleined.security_project.repository.PermissionRepository;
import com.elleined.security_project.repository.RoleRepository;
import com.elleined.security_project.repository.UserRepository;
import com.elleined.security_project.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    private final DefaultOAuth2UserService oAuth2UserService;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public String authenticate(String email,
                               String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if (!authentication.isAuthenticated())
            throw new UsernameNotFoundException("Invalid credential");

        return jwtService.generateToken(email);
    }

    public User register(UserRequest request) {
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.getRoleIds()));
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(request.getPermissionIds()));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(roles)
                .permissions(permissions)
                .build();

        userRepository.save(user);
        log.debug("Saving user success");
        return user;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email of " + email + " doesn't exists!"));
    }

    public boolean isEmailExists(String email) {
        return userRepository.findAll().stream()
                .map(User::getEmail)
                .anyMatch(email::equalsIgnoreCase);
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2LoginService() {
        return userRequest -> {
            OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
            log.debug("OAuth2 handled the request! {}", oAuth2User);

            String email = oAuth2User.getAttribute("email");
            String name = oAuth2User.getAttribute("name");
            List<String> roles = oAuth2User.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            if (this.isEmailExists(email)) {
                Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
                attributes.put("jwt-token", jwtService.generateToken(email));

                return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "email");
            }

            this.register(UserRequest.builder()
                    .name(name)
                    .email(email)
                    .roleIds(new HashSet<>())
                    .permissionIds(new HashSet<>())
                    .build());

            Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
            attributes.put("jwt-token", jwtService.generateToken(email));

            return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "email");
        };
    }
}
