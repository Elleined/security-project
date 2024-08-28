package com.elleined.security_project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "tbl_role")

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Role extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false,
            updatable = false,
            unique = true
    )
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
