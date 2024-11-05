package com.example.authentication.factory;


import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Component
public class JwtToken {

    private final UUID id;
    private final String subject;
    private final List<String> authorities;
    private final Instant createdAt;
    private final Instant expiresAt;

    public JwtToken() {
        this.id = UUID.randomUUID();
        this.subject = "";
        this.authorities = List.of();
        this.createdAt = Instant.now();
        this.expiresAt = createdAt.plusSeconds(600);
    }

    public JwtToken(UUID id, String subject, List<String> authorities, Instant createdAt, Instant expiresAt) {
        this.id = id;
        this.subject = subject;
        this.authorities = authorities;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return String.format("Token{id=%s, subject='%s', authorities=%s, createdAt=%s, expiresAt=%s}",
                id, subject, authorities, createdAt, expiresAt);
    }

}