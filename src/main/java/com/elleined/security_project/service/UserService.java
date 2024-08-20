package com.elleined.security_project.service;

import com.elleined.security_project.jwt.JWTService;
import com.elleined.security_project.model.User;
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
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final DefaultOAuth2UserService oAuth2UserService;
    private final OidcUserService oidcUserService;

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
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(request.getRoles())
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

            if (this.isEmailExists(email))
                return this.getByEmail(email);

            return this.register(UserRequest.builder()
                    .name(name)
                    .email(email)
                    .roles(roles)
                    .build());
        };
    }
}
