package com.example.user.controller;

import com.example.redis.device.service.DeviceTokenService;
import com.example.user.User;
import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import com.example.utils.jwt.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final DeviceTokenService deviceTokenService;

    private final UserMapper userMapper;

    @Value("${jwt.token-prefix}")
    private String tokenPrefix;

    @Value("${jwt.access.header-string}")
    private String accessHeaderString;
    @Value("${jwt.refresh.header-string}")
    private String refreshHeaderString;

    @PostMapping("/auth/kakao")
    public ResponseEntity<String> postKakaoLogin(@RequestBody UserRequest loginRequest, HttpServletRequest request) {
        return handleOAuthLogin(request, userMapper.mapToKakaoLoginUser(loginRequest));
    }

    @PostMapping("/auth/google")
    public ResponseEntity<String> postGoogleLogin(@RequestBody UserRequest loginRequest, HttpServletRequest request) {
        return handleOAuthLogin(request, userMapper.mapToGoogleLoginUser(loginRequest));
    }


    @GetMapping("/user")
    public ResponseEntity<Object> getUser(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.getUser(new User.UserId(userId));
        UserResponse responseDto = userMapper.mapToUserResponseDto(user);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> postLogin(@RequestBody UserRequest loginRequest, HttpServletRequest request) {
        String deviceToken = request.getHeader("device_token");
        User user = userMapper.mapToDefaultLoginUser(loginRequest);
        User loginedDefaultUser = userService.loginDefaultUser(user);

        if(loginedDefaultUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 잘못되었습니다.");
        }

        String accessToken= jwtTokenUtil.createAccessToken(loginedDefaultUser);
        String refreshToken= jwtTokenUtil.createRefreshToken(loginedDefaultUser);

        if (accessToken == null || refreshToken == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("토큰 생성에 실패했습니다.");
        }

        deviceTokenService.setDeviceToken(String.valueOf(loginedDefaultUser.getId()),deviceToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add(accessHeaderString, tokenPrefix + accessToken);
        headers.add(refreshHeaderString, tokenPrefix + refreshToken);
        return ResponseEntity.ok().headers(headers).body("success");
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Object> logOut(HttpServletRequest request){
        String deviceToken = request.getHeader("device_token");
        String userId = String.valueOf(request.getAttribute("userId"));
        deviceTokenService.removeDeviceToken(userId,deviceToken);
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/register")
    public ResponseEntity<Object> postRegister(@RequestBody UserRequest createUserRequest) {
        User user = userMapper.mapToDefaultRegisterUser(createUserRequest);
        userService.registUser(user);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body("success");
    }

    private ResponseEntity<String> handleOAuthLogin(HttpServletRequest request, User user) {
        String deviceToken = request.getHeader("device_token");
        User loginedUser = userService.loginOAuthUser(user);
        String accessToken = jwtTokenUtil.createAccessToken(loginedUser);
        String refreshToken = jwtTokenUtil.createRefreshToken(loginedUser);
        deviceTokenService.setDeviceToken(String.valueOf(loginedUser.getId()), deviceToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add(accessHeaderString, tokenPrefix + accessToken);
        headers.add(refreshHeaderString, tokenPrefix + refreshToken);
        return ResponseEntity.ok().headers(headers).body("success");
    }

}