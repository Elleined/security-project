package com.elleined.security_project.config;

import com.elleined.security_project.jwt.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    private final JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Only use for JWT
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Only use for JWT
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/login/**", "/register/**").permitAll()

                        .requestMatchers("/rbac/admin/**").hasRole("ADMIN")
                        .requestMatchers("/rbac/user/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .build();
    }
}

// OAut2 Login
// OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2LoginService in parameter
//.oauth2Login(oc -> oc
//        .loginPage("/login") // For custom login page but this is so complicated
//                         .defaultSuccessUrl("/login/oauth/success") // For custom login page but this is so complicated
//                         .failureUrl("/login/oauth/failed") // For custom login page but this is so complicated
//                        .userInfoEndpoint(uiec -> uiec.userService(oauth2LoginService)))