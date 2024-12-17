package com.project.shopapp.repositories;

import com.project.shopapp.models.Permission;
import com.project.shopapp.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface PermissionRepository  extends BaseRepository<Permission, Long> {

    @Query("SELECT p FROM Permission p WHERE p.resource = :resource AND p.method = :method AND p.role.name = :roleName")
    Optional<Permission> findPermission(String resource, String method, String roleName);

    @Query("SELECT p FROM Permission p JOIN Role r ON r.id = p.role.id WHERE r.name = 'GUEST'")
    Collection<Permission> byPassToken();
}
