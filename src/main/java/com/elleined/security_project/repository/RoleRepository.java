package com.elleined.security_project.repository;

import com.elleined.security_project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}