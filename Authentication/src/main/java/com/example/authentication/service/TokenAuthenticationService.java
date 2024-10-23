/*
package com.example.authentication.service;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenAuthenticationService {

    private final RedisTokenService redisTokenService;

    public TokenAuthenticationService(RedisTokenService redisTokenService) {
        this.redisTokenService = redisTokenService;
    }

    public void authenticateUser(Authentication authentication, String token) {
        String tokenKey = generateTokenKey(authentication.getName());
        redisTokenService.saveToken(tokenKey, token, Duration.ofHours(1)); // Токен живет 1 час
    }

    public boolean isTokenValid(String username, String token) {
        String tokenKey = generateTokenKey(username);
        String storedToken = redisTokenService.getToken(tokenKey);
        return token.equals(storedToken);
    }

    private String generateTokenKey(String username) {
        return "auth-token:" + username;
    }
}*/
