package com.project.shopapp.services.implement;

import com.project.shopapp.models.Permission;
import com.project.shopapp.repositories.PermissionRepository;
import com.project.shopapp.services.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements IPermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Collection<Permission> iPermissionByPassToken() {
        return permissionRepository.byPassToken();
    }

    @Override
    public Optional<Permission> iFindPermission(String resource, String method, String roleName) {
        return permissionRepository.findPermission(resource, method, roleName);
    }
}
