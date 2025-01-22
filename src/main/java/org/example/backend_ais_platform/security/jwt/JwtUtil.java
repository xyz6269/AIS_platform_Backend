package org.example.backend_ais_platform.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtil {

    // Generate a secure key for signing
    private static final SecretKey SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SIGNING_KEY)
                .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
                .requireIssuer(SIGNING_KEY.toString())
                .build()
                .parseEncryptedClaims(token)
                .getPayload();
    }

    public static boolean isTokenValid(String token) {
        return !isExpired(token);
    }

    private static boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}