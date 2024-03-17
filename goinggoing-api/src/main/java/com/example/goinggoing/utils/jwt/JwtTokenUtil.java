package com.example.goinggoing.utils.jwt;
import com.example.goinggoingdomain.domain.user.User;
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

    public String createAccessToken(User user) { //(2-1)
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        String accessToken = Jwts.builder()
                .setSubject(user.getUserEmail()) // 정보 저장
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                // 만료 시간 설정 (현재 시간 + 1시간)
                .signWith(secretKey, SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .setId(String.valueOf(user.getUserId()))
                .compact();
        return accessToken;
    }
    public String createRefreshToken(User user) { //(2-1)
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        String refreshToken = Jwts.builder()
                .setSubject(user.getUserEmail())  // JWT의 주체 설정
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                // 만료 시간 설정 (현재 시간 + 14일)
                .signWith(secretKey, SignatureAlgorithm.HS256)// 서명 알고리즘과 비밀 키 설정
                .setId(String.valueOf(user.getUserId()))
                .compact();
        return refreshToken;
    }
}
