package com.example.authentication.service;

import com.example.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService{

    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    @Autowired
    public TokenService(RedisTemplate<String, String> redisTemplate, UserRepository userRepository) {
        this.redisTemplate = redisTemplate;
        this.userRepository = userRepository;
    }

    public void deactivateToken(String tokenId, long expirationTime) {
        redisTemplate.opsForValue().set(tokenId, "deactivated", expirationTime, TimeUnit.SECONDS);
    }

    public boolean isTokenDeactivated(String tokenId) {
        return redisTemplate.hasKey(tokenId);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}