package com.example.user.controller;

import com.example.redis.device.service.DeviceTokenService;
import com.example.user.User;
import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.service.UserService;
import com.example.utils.jwt.JwtTokenUtil;
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


    @Value("${jwt.token-prefix}")
    private String tokenPrefix;

    @Value("${jwt.access.header-string}")
    private String accessHeaderString;
    @Value("${jwt.refresh.header-string}")
    private String refreshHeaderString;

    @PostMapping("/auth/kakao")
    public ResponseEntity<String> postKakaoLogin(
            @RequestBody UserRequest request,
            @RequestHeader String deviceToken
    ) {
        return handleOAuthLogin(request.toKakaoLoginUser(deviceToken));
    }

    @PostMapping("/auth/google")
    public ResponseEntity<String> postGoogleLogin(
            @RequestBody UserRequest request,
            @RequestHeader String deviceToken
    ) {
        return handleOAuthLogin(request.toGoogleLoginUser(deviceToken));
    }


    @GetMapping("/user")
    public ResponseEntity<Object> getUser(
            @RequestAttribute Long userId
    ) {
        User user = userService.getUser(new User.UserId(userId));
        return ResponseEntity.ok().body(UserResponse.of(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody UserRequest request,
            @RequestHeader String deviceToken
    ) {
        User loginedUser = userService.loginDefaultUser(request.toDefaultLoginUser(deviceToken));
        deviceTokenService.setDeviceToken(loginedUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add(accessHeaderString, tokenPrefix + jwtTokenUtil.createAccessToken(loginedUser));
        headers.add(refreshHeaderString, tokenPrefix + jwtTokenUtil.createRefreshToken(loginedUser));
        return ResponseEntity.ok().headers(headers).body("success");
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Object> logOut(
            @RequestAttribute Long userId,
            @RequestHeader String deviceToken
    ){
        deviceTokenService.removeDeviceToken(new User.UserId(userId),deviceToken);
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/register")
    public ResponseEntity<Object> postRegister(@RequestBody UserRequest request) {
        userService.registUser(request.toDefaultRegisterUser());
        return ResponseEntity.ok().body("success");
    }

    private ResponseEntity<String> handleOAuthLogin(
            User user
    ) {
        User loginedUser = userService.loginOAuthUser(user);
        deviceTokenService.setDeviceToken(loginedUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add(accessHeaderString, tokenPrefix + jwtTokenUtil.createAccessToken(loginedUser));
        headers.add(refreshHeaderString, tokenPrefix + jwtTokenUtil.createRefreshToken(loginedUser));
        return ResponseEntity.ok().headers(headers).body("success");
    }

}