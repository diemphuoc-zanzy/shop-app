package com.project.shopapp.utils;

import com.project.shopapp.confiuration.exception.UnauthorizedAccessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Value("${jwt.accessToken}")
    private int accessToken;

    @Value("${jwt.refreshToken}")
    private int refreshToken;

    @Value("${jwt.secretKey}")
    private String secretKey;

    private String generate(Map<String, Object> claims, String subject, int expiration) {
        return Jwts.builder()
               .setClaims(claims)
               .setSubject(subject)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
               .signWith(getSignInKey(), SignatureAlgorithm.HS256)
               .compact();
    }

    public String generateAccessToken(com.project.shopapp.models.User user) {
        // Tạo token JWT
        // properties -> class
        Map<String, Object> claims = new HashMap<>();
        String phoneNumber = user.getPhoneNumber();
        claims.put("phoneNumber", phoneNumber);
        try {
            return generate(claims, phoneNumber, accessToken);
        } catch (Exception e) {
            logger.error("Error generating JWT access token: {}", e.getMessage());
            return null;
        }
    }


    // Tạo Refresh Token
    public String generateRefreshToken(com.project.shopapp.models.User user) {
        // Tạo token JWT
        // properties -> class
        Map<String, Object> claims = new HashMap<>();
        String phoneNumber = user.getPhoneNumber();
        claims.put("phoneNumber", phoneNumber);
        try {
            return generate(claims, phoneNumber, accessToken);
        } catch (Exception e) {
            logger.error("Error generating JWT refresh token: {}", e.getMessage());
            return null;
        }
    }

    // Encode JWT
    public String encode(String name,
                         String value,
                         int maxAge) {
        // Tạo JWT với claims và các thông tin bổ sung
        return Optional
                .of(generate(Map.of(name, value), name, maxAge))
                .orElseThrow(() -> new UnauthorizedAccessException("Unexpected encode token"));
    }

    public String decode(String token) {
        return Optional
                .of(extractClaim(token, Claims::getSubject))
                .orElseThrow(() -> new UnauthorizedAccessException("Unexpected decode token"));
    }


    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> clamsResolve) {
        final Claims claims = this.extractAllClaims(token);
        return clamsResolve.apply(claims);
    }

    public String extractPhoneNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //check phone number
    public boolean validateToken(String token, UserDetails userDetails) {
        final String phoneNumber = this.extractPhoneNumber(token);
        return phoneNumber.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    //check expiration
    public boolean isTokenExpired(String token) {
        final Date expiration = this.extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
