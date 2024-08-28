package com.elleined.security_project.service.permission;

import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public interface PermissionService {
    Permission getById(int id);
    List<Permission> getAll();
    Page<Permission> getAll(Pageable pageable);

    void addPermission(User user, Permission permission);
    void removePermission(User user, Permission permission);

    default void addAllPermissions(User user, List<Permission> permissions) {
        permissions.forEach(permission -> this.addPermission(user, permission));
    }

    default void addAllPermissions(User user, Permission... permissions) {
        Stream.of(permissions).forEach(permission -> this.addPermission(user, permission));
    }

    default boolean hasPermission(User user, Permission permission) {
        return user.getPermissions().contains(permission);
    }

    default boolean hasAnyPermission(User user, Permission... permissions) {
        return Stream.of(permissions).anyMatch(user.getPermissions()::contains);
    }

    default boolean hasAnyPermission(User user, Collection<Permission> permissions) {
        return user.getPermissions().stream().anyMatch(permissions::contains);
    }
}
