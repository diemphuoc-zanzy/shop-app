package com.project.shopapp.services.implement;

import com.project.shopapp.common.ROLE_LICENSE;
import com.project.shopapp.confiuration.exception.UnauthorizedAccessException;
import com.project.shopapp.models.Role;
import com.project.shopapp.repositories.RoleRepository;
import com.project.shopapp.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;

    @Value("${default.role}")
    private String defaultRole;

    @Override
    public Role getRole(Long roleId) {
        if (roleId == null) {
            return roleRepository.findByName(defaultRole);
        }

        return roleRepository.findById(roleId)
                .filter(role -> ROLE_LICENSE.isNotAdmin(role.getName()))
                .orElseThrow(() -> new UnauthorizedAccessException("Role Unauthorized Access"));
    }
}
