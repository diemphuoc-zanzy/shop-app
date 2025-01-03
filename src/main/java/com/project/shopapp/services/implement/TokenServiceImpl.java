package com.project.shopapp.services.implement;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.confiuration.exception.UnauthorizedAccessException;
import com.project.shopapp.dtos.request.TokenRequestDto;
import com.project.shopapp.models.Token;
import com.project.shopapp.models.User;
import com.project.shopapp.repositories.TokenRepository;
import com.project.shopapp.services.ITokenService;
import com.project.shopapp.services.implement.base.BaseServiceImpl;
import com.project.shopapp.specs.TokenSpec;
import com.project.shopapp.utils.JwtTokenUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl extends BaseServiceImpl implements ITokenService {

    @Value("${jwt.maxTokens}")
    private int maxTokens;

    private final TokenRepository tokenRepository;
    private final TokenSpec tokenSpec;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    @Transactional
    public String iCreateAccessToken(User user) {
        Token accessToken = this.getAccessToken(user);
        tokenRepository.save(accessToken);
        return accessToken.getToken();
    }

    @Override
    @Transactional
    public String iCreateRefreshToken(User user) {
        Token refreshToken = this.getRefreshToken(user);
        tokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public Token getRefreshToken(User user) {
        TokenRequestDto requestRefreshToken = new TokenRequestDto(
                user.getPhoneNumber(),
                TOKEN_TYPE.REFRESH_TOKEN.name()
        );

        Optional<Token> refreshTokenOpt = tokenRepository.findOne(tokenSpec.getTokens(requestRefreshToken));

        if (refreshTokenOpt.isPresent()) {
            return refreshTokenOpt.get();
        }

        String refreshToken = jwtTokenUtils.generateAccessToken(user);
        Date accessExpiration = jwtTokenUtils.extractExpiration(refreshToken);

        return new Token(refreshToken, TOKEN_TYPE.REFRESH_TOKEN, accessExpiration.toInstant().toEpochMilli(), user);
    }

    public Token getAccessToken(User user) {
        TokenRequestDto requestAccessToken = new TokenRequestDto(
                user.getPhoneNumber(),
                TOKEN_TYPE.ACCESS_TOKEN.name()
        );

        List<Token> tokens = tokenRepository.findAll(tokenSpec.getTokens(requestAccessToken));
        this.enforceAccessTokenLimit(tokens);

        String accessToken = jwtTokenUtils.generateAccessToken(user);
        Date accessExpiration = jwtTokenUtils.extractExpiration(accessToken);

        return new Token(accessToken, TOKEN_TYPE.ACCESS_TOKEN, accessExpiration.toInstant().toEpochMilli(), user);
    }

    public void enforceAccessTokenLimit(List<Token> tokens) {
        if (tokens.size() < maxTokens)
            return;

        Token deleteToken = tokens.get(0);
        deleteToken.updateRecordStatus(RECORD_STATUS.DELETED);
        tokenRepository.save(deleteToken);
    }

    @Override
    public User iGetUserByToken(String token) {
        return tokenRepository.findByToken(token)
                .map(Token::getUser)
                .orElseThrow(() -> new UnauthorizedAccessException("Token " + token + "not found"));
    }
}
