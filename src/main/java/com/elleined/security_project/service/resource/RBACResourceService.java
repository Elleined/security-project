package com.elleined.security_project.service.resource;

import com.elleined.security_project.model.Permission;
import com.elleined.security_project.model.User;
import com.elleined.security_project.service.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.ResourceAccessException;

@Slf4j
@Service
@Primary
@Validated
@Transactional
public class RBACResourceService implements ResourceService {
    private final PermissionService permissionService;
    private final ResourceService resourceService;

    public RBACResourceService(PermissionService permissionService,
                               @Qualifier("resourceServiceImpl") ResourceService resourceService) {

        this.permissionService = permissionService;
        this.resourceService = resourceService;
    }

    @Override
    public String save(User user) {
        final Permission permissionAllowed = permissionService.getByName("RESOURCE:CREATE");
        if (!PermissionService.hasPermission(user, permissionAllowed))
            throw new ResourceAccessException("Access Denied");

        return resourceService.save(user);
    }

    @Override
    public String delete(User user) {
        final Permission permissionAllowed = permissionService.getByName("RESOURCE:DELETE");
        if (!PermissionService.hasPermission(user, permissionAllowed))
            throw new ResourceAccessException("Access Denied");

        return resourceService.delete(user);
    }
}
