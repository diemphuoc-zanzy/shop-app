package com.project.shopapp.services.implement;

import com.project.shopapp.common.constant.MessageKeys;
import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.models.Role;
import com.project.shopapp.repositories.RoleRepository;
import com.project.shopapp.services.IRoleService;
import com.project.shopapp.services.implement.base.BaseServiceImpl;
import com.project.shopapp.specs.RoleSpec;
import com.project.shopapp.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;

    private final RoleSpec roleSpec;

    @Value("${default.role}")
    private String defaultRole;

    @Override
    public Role iFindById(Long roleId) {
        return roleRepository
                .findOne(roleSpec.getRoleById(roleId))
                .orElseThrow(() -> new NotFoundException(this.message(MessageKeys.ROLE.UNAUTHORIZED_ACCESS, roleId)));
    }

    @Override
    public Role iFindByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role getRole(Long roleId) {
        if (roleId == null)
            return this.iFindByName(defaultRole);

        return this.iFindById(roleId);
    }
}
