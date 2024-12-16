package com.project.shopapp.repositories;

import com.project.shopapp.repositories.base.BaseRepository;
import com.project.shopapp.models.Role;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends BaseRepository<Role, Long> {

    @Query("SELECT role FROM Role role WHERE role.name = :name")
    Role findByName(String name);
}
