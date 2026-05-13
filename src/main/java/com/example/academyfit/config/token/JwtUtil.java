package com.example.academyfit.config.token;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${api.security.token.secret:chavePadraoParaTeste123}")
    private String secret;

    private long expiration = 3600000; // 1 hora em milissegundos

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public boolean tokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("Erro na validação do token: " + e.getMessage());
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes())
                .parseClaimsJws(token).getBody().getSubject();
    }
}