package com.elleined.security_project.service.role;

import com.elleined.security_project.model.Role;
import com.elleined.security_project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    Role getById(int id);
    List<Role> getAll();
    Page<Role> getAll(Pageable pageable);

    void addRole(User user, Role role);
    void removeRole(User user, Role role);


}
