package com.example.authentication.domain;
import jdk.jshell.Snippet;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Setter
@Getter


public class Token {
    private final UUID id;
    private final String username;
    private final List<String> authorities;
    private final Instant createdAt;
    private final Instant expiresAt;

    public Token(UUID id, String username, List<String> authorities, Instant createdAt, Instant expiresAt) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public Snippet getToken() {
        return null;
    }
}