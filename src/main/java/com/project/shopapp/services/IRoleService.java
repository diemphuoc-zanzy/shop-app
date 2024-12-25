package com.project.shopapp.services;

import com.project.shopapp.models.Role;
import com.project.shopapp.models.Role;

public interface IRoleService {
    Role iFindById(Long roleId);
    Role iFindByName(String roleName);
    Role getRole(Long roleId);
}
