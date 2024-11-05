package com.example.authentication.config;

import com.example.authentication.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenAuthenticationUserDetailsService implements UserDetailsService, AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final TokenService tokenService;

    @Autowired
    public TokenAuthenticationUserDetailsService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = tokenService.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }


        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                user.get().getAuthorities()
        );
    }

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        return null;
    }
}
