package com.elleined.security_project.repository;

import com.elleined.security_project.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}