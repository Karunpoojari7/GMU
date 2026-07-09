package com.gmu.app.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gmu.app.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    @Value("${jwt.access.expiration}")
    private long accessTokenExpiry;

    @Value("${jwt.internal.expiration}")
    private long internalTokenExpiry;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiry))
                .signWith(secretKey)
                .compact();
    }

    public String generateInternalToken(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + internalTokenExpiry))
                .signWith(secretKey)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Token expired");
        } catch (UnsupportedJwtException ex) {
            throw new RuntimeException("Unsupported token");
        } catch (MalformedJwtException ex) {
            throw new RuntimeException("Malformed token");
        } catch (SecurityException ex) {
            throw new RuntimeException("Invalid signature");
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Empty token");
        }
    }

    public String extractSubject(String token) {
        return validateToken(token).getSubject();
    }

    public Claims extractClaims(String token) {
        return validateToken(token);
    }

    public String extractRole(String token) {
        return validateToken(token).get("role", String.class);
    }

    public String extractService(String token) {
        return validateToken(token).get("service", String.class);
    }

    public boolean isTokenExpired(String token) {
        return validateToken(token).getExpiration().before(new Date());
    }

    public String generateAccessToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        // Lowercase aliases — required by JwtFilter / JwtGatewayFilter
        claims.put("userId", user.getEmpId());
        claims.put("role", user.getUserGroup());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiry))
                .signWith(secretKey)
                .compact();
    }
}