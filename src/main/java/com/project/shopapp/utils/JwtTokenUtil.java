package com.project.shopapp.utils;

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
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(com.project.shopapp.models.User user) {
        // Tạo token JWT
        // properties -> class
        Map<String, Object> claims = new HashMap<>();
        claims.put("phoneNumber", user.getPhoneNumber());
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getPhoneNumber())
                    // thời gian hiện tại + với 30 ngày + 1000(chuyển đổi qua giây)
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            logger.error("Error generating JWT token: {}", e.getMessage());
            return null;
        }
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

    public  <T> T extractClaim(String token, Function<Claims, T> clamsResolve) {
        final Claims claims = this.extractAllClaims(token);
        return clamsResolve.apply(claims);
    }

    public String extractPhoneNumber(String token) {
        return extractClaim(token, Claims::getSubject);
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
