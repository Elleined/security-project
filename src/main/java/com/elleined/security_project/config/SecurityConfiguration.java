package com.elleined.security_project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Only use for JWT
                // .addFilterBefore(, UsernamePasswordAuthenticationFilter.class) // Only use for JWT
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/login/**", "/register/**").permitAll()
                        .requestMatchers("/role-authorization/admin/**").hasRole("ADMIN")
                        .requestMatchers("/role-authorization/user/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .build();
    }
}
