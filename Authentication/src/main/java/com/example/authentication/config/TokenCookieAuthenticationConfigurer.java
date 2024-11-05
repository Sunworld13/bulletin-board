package com.example.authentication.config;


import com.example.authentication.factory.JwtToken;
import com.example.authentication.factory.TokenCookieJweStringDeserializer;
import com.example.authentication.service.TokenService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.csrf.CsrfFilter;

import java.util.Date;
import java.util.function.Function;

public class TokenCookieAuthenticationConfigurer
    extends AbstractHttpConfigurer<TokenCookieAuthenticationConfigurer, HttpSecurity> {

    private Function<String, JwtToken> tokenCookieStringDeserializer;

    private final TokenService tokenService;


    public TokenCookieAuthenticationConfigurer(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.logout(logout -> logout.addLogoutHandler(
                        new CookieClearingLogoutHandler("__Host-auth-token"))
                .addLogoutHandler((request, response, authentication) -> {
                    if (authentication != null &&
                            authentication.getPrincipal() instanceof JwtToken user) {
                        tokenService.deactivateToken(String.valueOf(user.getId()),
                                user.getExpiresAt().getEpochSecond());

                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }
                }));
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        var cookieAuthenticationFilter = new AuthenticationFilter(
                builder.getSharedObject(AuthenticationManager.class),
                new TokenCookieAuthenticationConverter(this.tokenCookieStringDeserializer));
        cookieAuthenticationFilter.setSuccessHandler((request, response, authentication) -> {
        });
        cookieAuthenticationFilter.setFailureHandler(
                new AuthenticationEntryPointFailureHandler(
                        new Http403ForbiddenEntryPoint()
                )
        );

        var authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(
                new TokenAuthenticationUserDetailsService(tokenService));

        builder.addFilterAfter(cookieAuthenticationFilter, CsrfFilter.class)
                .authenticationProvider(authenticationProvider);
    }

    public TokenCookieAuthenticationConfigurer tokenCookieStringDeserializer(
            TokenCookieJweStringDeserializer tokenCookieStringDeserializer) {
        this.tokenCookieStringDeserializer = tokenCookieStringDeserializer;
        return this;
    }

}
