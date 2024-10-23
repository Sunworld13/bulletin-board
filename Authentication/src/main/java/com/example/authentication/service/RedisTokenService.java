/*
package com.example.authentication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisTokenService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisTokenService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void saveToken(String tokenKey, String token, Duration ttl) {
        redisTemplate.opsForValue().set(tokenKey, token, ttl);
    }


    public String getToken(String tokenKey) {
        return (String) redisTemplate.opsForValue().get(tokenKey);
    }


    public void deleteToken(String tokenKey) {
        redisTemplate.delete(tokenKey);
    }


    public boolean tokenExists(String tokenKey) {
        return redisTemplate.hasKey(tokenKey);
    }
}*/
