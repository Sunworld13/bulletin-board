package com.example.authentication.config;

import com.example.authentication.domain.User;
import com.example.authentication.domain.UserAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class CookieAuthenticationService {
    @Autowired
    private RedisTemplate<String, User> redisTemplate;


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = redisTemplate.opsForValue().get(username);
                if (user == null) {
                    throw new UsernameNotFoundException(username + " NOT FOUND");
                }
                return new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities().stream()
                                .map(UserAuthority::getAuthority)
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList())
                );
            }
        });
    }

}
