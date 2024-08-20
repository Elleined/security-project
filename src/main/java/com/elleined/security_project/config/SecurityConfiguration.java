package com.elleined.security_project.config;

import com.elleined.security_project.jwt.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2LoginService) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Only use for JWT
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Only use for JWT
                .oauth2Login(oc -> oc
                        // .loginPage("/login") // For custom login page but this is so complicated
                        // .defaultSuccessUrl("/login/oauth/success") // For custom login page but this is so complicated
                        // .failureUrl("/login/oauth/failed") // For custom login page but this is so complicated
                        .userInfoEndpoint(uiec -> uiec.userService(oauth2LoginService)))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/login/**", "/register/**").permitAll()
                        .requestMatchers("/role-authorization/admin/**").hasRole("ADMIN")
                        .requestMatchers("/role-authorization/user/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .build();
    }
}
