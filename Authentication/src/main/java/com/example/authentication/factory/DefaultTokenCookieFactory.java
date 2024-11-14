package com.example.authentication.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.function.Function;

public class DefaultTokenCookieFactory implements Function<Authentication, JwtToken> {

   // private Duration tokenTtl = Duration.ofDays(1);
    @Value("${jwt.lifetime}")
    private String tokenTtl;

    @Override
    public JwtToken apply(Authentication authentication) {
        var now = Instant.now();
        Duration duration = Duration.parse(tokenTtl);
        return new JwtToken(UUID.randomUUID(),
                authentication.getName(),
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList(),
                now, now.plus(duration));
    }

}