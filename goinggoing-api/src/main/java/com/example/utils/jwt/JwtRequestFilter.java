package com.example.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;
    @Value("${jwt.access.header-string}")
    private String ACCESS_HEADER_STRING;
    @Value("${jwt.refresh.header-string}")
    private String REFRESH_HEADER_STRING;
    @Value("${jwt.secret}")
    private String SECRET;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtAccessHeader = request.getHeader(ACCESS_HEADER_STRING);
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/api/login") || requestURI.equals("/api/oauth/token") || requestURI.equals("/api/register") || requestURI.equals("/api/auth/kakao")) {
            filterChain.doFilter(request, response);
            return;
        }
        Long userId = null;
        try {
            String validAccessToken = validateToken(jwtAccessHeader);
            if (validAccessToken != null) {
                userId = Long.valueOf(validAccessToken);
            }
        } catch (ExpiredJwtException e) {
            // 토큰 만료 처리를 여기에 추가
            logger.error("Expired JWT Token: {}");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String errorMessage = "{\"message\": \"error_token\"}";
            response.getWriter().write(errorMessage);
            return;
        } catch (JwtException e) {
            // 유효하지 않은 토큰 처리를 여기에 추가
            logger.error("Invalid JWT Token: {}");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String errorMessage = "{\"message\": \"error_need_login\"}";
            response.getWriter().write(errorMessage);
            return;
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String errorMessage = "{\"message\": \"error_need_login\"}";
            response.getWriter().write(errorMessage);
            return;
        }
        request.setAttribute("userId", userId);
        filterChain.doFilter(request, response);
    }

    public String validateToken(String validToken) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        String token = validToken.replace(TOKEN_PREFIX, "");
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getId();
    }
}
