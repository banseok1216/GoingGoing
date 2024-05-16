package com.example.utils.jwt;
import com.example.user.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.access.expiration-time}")
    private int ACCESS_EXPIRATION_TIME;
    @Value("${jwt.refresh.expiration-time}")
    private int REFRESH_EXPIRATION_TIME;

    public String createAccessToken(User user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        String accessToken = Jwts.builder()
                .setSubject(user.getUserEmail())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setId(String.valueOf(user.getId()))
                .compact();
        return accessToken;
    }
    public String createRefreshToken(User user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        String refreshToken = Jwts.builder()
                .setSubject(user.getUserEmail())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setId(String.valueOf(user.getId()))
                .compact();
        return refreshToken;
    }
}
