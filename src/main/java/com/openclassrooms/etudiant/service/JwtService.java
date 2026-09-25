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
        // Recupération de l'algorithme de signature et de la date actuelle
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());

        // Décodage de la clé secrète et création de la clé de signature
        byte[] apiKey = Base64.getDecoder().decode(secret);
        Key signingKey = new SecretKeySpec(apiKey, signatureAlgorithm.getJcaName());

        // Création du token JWT avec le sujet, la date d'émission et la signature
        JwtBuilder builder = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        // Retourne le token JWT sous forme de chaîne de caractères
        return builder.compact();
    }
}
