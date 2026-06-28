package br.com.marcos.api.infra.auth;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import br.com.marcos.api.domain.gateway.AuthenticationGateway;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class AuthenticationJwt implements AuthenticationGateway {
    private final Key key;
    private final long expiration;

    public AuthenticationJwt(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {

        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    @Override
    public String generateToken(Long userId) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + this.expiration);
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now).setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

}
