package com.project.shopapp.repositories.base;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<Type, Key> extends CrudRepository<Type, Key>, JpaSpecificationExecutor<Type> {
}
