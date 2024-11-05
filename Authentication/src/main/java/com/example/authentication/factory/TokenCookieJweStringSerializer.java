package com.example.authentication.factory;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.function.Function;

public class TokenCookieJweStringSerializer implements Function<JwtToken, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCookieJweStringSerializer.class);

    private final JWEEncrypter jweEncrypter;

    private JWEAlgorithm jweAlgorithm = JWEAlgorithm.DIR;

    private EncryptionMethod encryptionMethod = EncryptionMethod.A128GCM;

    public TokenCookieJweStringSerializer(JWEEncrypter jweEncrypter) {
        this.jweEncrypter = jweEncrypter;
    }

    public TokenCookieJweStringSerializer(JWEEncrypter jweEncrypter, JWEAlgorithm jweAlgorithm, EncryptionMethod encryptionMethod) {
        this.jweEncrypter = jweEncrypter;
        this.jweAlgorithm = jweAlgorithm;
        this.encryptionMethod = encryptionMethod;
    }

    @Override
    public String apply(JwtToken token) {
        var jwsHeader = new JWEHeader.Builder(this.jweAlgorithm, this.encryptionMethod)
                .keyID(token.getId().toString())
                .build();
        var claimsSet = new JWTClaimsSet.Builder()
                .jwtID(token.getId().toString())
                .subject(token.getSubject())
                .issueTime(Date.from(token.getCreatedAt()))
                .expirationTime(Date.from(token.getExpiresAt()))
                .claim("authorities", token.getAuthorities())
                .build();
        var encryptedJWT = new EncryptedJWT(jwsHeader, claimsSet);
        try {
            encryptedJWT.encrypt(this.jweEncrypter);

            return encryptedJWT.serialize();
        } catch (JOSEException exception) {
            LOGGER.error(exception.getMessage(), exception);
        }

        return null;
    }

    public void setJweAlgorithm(JWEAlgorithm jweAlgorithm) {
        this.jweAlgorithm = jweAlgorithm;
    }

    public void setEncryptionMethod(EncryptionMethod encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }
}