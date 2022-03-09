package com.madirex.hairsalonserver.config.security.jwt;


import com.madirex.hairsalonserver.model.User;
import com.madirex.hairsalonserver.model.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Log
@Component
public class JwtTokenProvider {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String TOKEN_TYPE = "JWT";

    @Value("${jwt.secret:jwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargo" +
            "jwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargo" +
            "jwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargojwtDebeSerLargo}")
    private String jwtSecreto;
    @Value("${jwt.token-expiration:86400}")
    private int jwtDurationInSeconds;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDurationInSeconds * 1000));

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .claim("name", user.getName())
                .claim("roles", user.getRoles().stream()
                        .map(UserRole::name)
                        .collect(Collectors.joining(", "))
                )
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.info("Error en la firma del token JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.info("Token malformado: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.info("El token ha expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Token JWT no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims vacío");
        }
        return false;
    }
}
