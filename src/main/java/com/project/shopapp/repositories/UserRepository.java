package com.project.shopapp.repositories;

import com.project.shopapp.repositories.base.BaseRepository;
import com.project.shopapp.models.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
