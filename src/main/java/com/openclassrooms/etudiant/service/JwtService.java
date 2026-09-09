package com.openclassrooms.etudiant.service;

import java.util.Date;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Service
public class JwtService {
    @Value ("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());

        byte[] apiKey = Base64.getDecoder().decode(secret);
        Key signingKey = new SecretKeySpec(apiKey, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }
}
