package com.example.utils.jwt;

import com.example.user.domain.User;
import com.example.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class JwtTokenController {
    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;
    @Value("${jwt.access.header-string}")
    private String Access_HEADER_STRING;
    @Value("${jwt.refresh.header-string}")
    private String Refresh_HEADER_STRING;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    public JwtTokenController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }
    @GetMapping("/token/refresh")
    public ResponseEntity<Object> RefreshToken(HttpServletRequest request) {
        Long userId = (Long)request.getAttribute("userId");
        User user = userService.getUser(new User.UserId(userId));
        HttpHeaders headers = new HttpHeaders();
        String refreshToken = jwtTokenUtil.createRefreshToken(user);
        String accessToken = jwtTokenUtil.createAccessToken(user);
        headers.add(Access_HEADER_STRING, TOKEN_PREFIX + refreshToken);
        headers.add(Refresh_HEADER_STRING, TOKEN_PREFIX + accessToken);
        return ResponseEntity.ok().headers(headers).body("success");
    }
}
