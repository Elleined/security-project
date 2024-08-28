package com.elleined.security_project.repository;

import com.elleined.security_project.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    @Query("SELECT permission FROM Permission permission WHERE permission.name IN :names")
    List<Permission> findAllByName(@Param("names") List<String> names);

    @Query("SELECT permission FROM Permission permission WHERE permission.name = :name")
    Optional<Permission> findByName(@Param("name") String name);
}