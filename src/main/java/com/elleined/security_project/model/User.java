package com.elleined.security_project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.*;

@Entity
@Table(name = "tbl_user")

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class User extends PrimaryKeyIdentity implements UserDetails, OidcUser {
    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "tbl_user_permission",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Set<Permission> permissions;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = this.getRoles().stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();

        List<SimpleGrantedAuthority> permissions = this.getPermissions().stream()
                .map(Permission::getName)
                .map(name -> STR."ROLE_\{name}")
                .map(SimpleGrantedAuthority::new)
                .toList();

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.addAll(roles);
        simpleGrantedAuthorities.addAll(permissions);

        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Map<String, Object> getClaims() {
        return Map.of();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }
}
