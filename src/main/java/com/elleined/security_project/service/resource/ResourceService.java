package com.elleined.security_project.service.resource;

import com.elleined.security_project.model.User;

public interface ResourceService {
    String save(User user);
    String delete(User user);

    // First approach for handling permission authorization
//    default String save(User user, Permission permission) {
//        if (!PermissionService.hasPermission(user, permission)) {
//            throw new ResourceAccessException("Access Denied");
//        }
//
//        return this.save(user);
//    }
//
//    default String save(User user, Permission... permissions) {
//        if (!PermissionService.hasAnyPermission(user, permissions)) {
//            throw new ResourceAccessException("Access Denied");
//        }
//
//        return this.save(user);
//    }
//
//    default String save(User user, List<Permission> permissions) {
//        if (!PermissionService.hasAnyPermission(user, permissions)) {
//            throw new ResourceAccessException("Access Denied");
//        }
//
//        return this.save(user);
//    }
//
//    default String delete(User user, Permission permission) {
//        if (!PermissionService.hasPermission(user, permission)) {
//            throw new ResourceAccessException("Access Denied");
//        }
//
//        return this.delete(user);
//    }
//
//    default String delete(User user, Permission... permissions) {
//        if (!PermissionService.hasAnyPermission(user, permissions)) {
//            throw new ResourceAccessException("Access Denied");
//        }
//
//        return this.delete(user);
//    }
//
//    default String delete(User user, List<Permission> permissions) {
//        if (!PermissionService.hasAnyPermission(user, permissions)) {
//            throw new ResourceAccessException("Access Denied");
//        }
//
//        return this.delete(user);
//    }
}
