package com.project.shopapp.repositories;

import com.project.shopapp.repositories.base.BaseRepository;
import com.project.shopapp.models.Token;

import java.util.Optional;

public interface TokenRepository extends BaseRepository<Token, Long> {

    Optional<Token> findByToken(String token);
}
