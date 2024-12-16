package com.project.shopapp.services;


import com.project.shopapp.models.Permission;

import java.util.Collection;
import java.util.Optional;

public interface IPermissionService {
    Collection<Permission> iPermissionByPassToken();
    Optional<Permission> iFindPermission(String resource, String method, String roleName);
}
