package org.aidiary.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aidiary.config.JwtProperties;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private SecretKey secretKey;
    private static final String SUBJECT = "subject";

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.warn("Token has expired: {}", e.getMessage());
            throw new ExpiredJwtTokenException("Token has expired");
        } catch (JwtException e) {
            log.warn("Invalid token: {}", e.getMessage());
            throw new InvalidJwtTokenException("Invalid token");
        }
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = extractClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtTokenException e) {
            return false;
        } catch (InvalidJwtTokenException e) {
            return false;
        }
    }

    public static class InvalidJwtTokenException extends RuntimeException {
        public InvalidJwtTokenException(String message) {
            super(message);
        }
    }

    public static class ExpiredJwtTokenException extends RuntimeException {
        public ExpiredJwtTokenException(String message) {
            super(message);
        }
    }
}