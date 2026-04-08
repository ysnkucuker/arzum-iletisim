package com.yasinkucuker.arzum_iletisim.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    // 32 byte (256 bit) Base64 encoded secret key
    private final String SECRET_BASE64 = "R2VucmF0ZURhdGFCYXNlNjRTZWNyZXRLZXlGb3JKV1Q=";

    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_BASE64));

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 saat
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}