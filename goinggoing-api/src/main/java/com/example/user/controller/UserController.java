package com.example.user.controller;

import com.example.redis.device.service.DeviceTokenService;
import com.example.user.User;
import com.example.user.dto.*;
import com.example.user.service.UserService;
import com.example.utils.jwt.JwtTokenUtil;
import com.example.utils.response.DefaultId;
import com.example.utils.response.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/v2")
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

    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil, DeviceTokenService deviceTokenService) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.deviceTokenService = deviceTokenService;
    }

    @PostMapping("/auth/kakao")
    public HttpResponse<DefaultId> postKakaoLogin(
            @RequestBody UserRequest.oauthLogin request,
            @RequestHeader String deviceToken
    ) {
        return handleOAuthLogin(request.toKakaoLoginUser(deviceToken));
    }
    @PostMapping("/auth/google")
    public HttpResponse<DefaultId> postGoogleLogin(
            @RequestBody UserRequest.oauthLogin request,
            @RequestHeader String deviceToken
    ) {
        return handleOAuthLogin(request.toGoogleLoginUser(deviceToken));
    }
    @GetMapping("/user")
    public HttpResponse<UserResponse> getUser(
            @RequestAttribute Long userId
    ) {
        User user = userService.getUser(new User.UserId(userId));
        return HttpResponse.success(UserResponse.of(user));
    }
    @PostMapping("/login")
    public HttpResponse<DefaultId> login(
            @RequestBody UserRequest.defaultLogin request,
            @RequestHeader String deviceToken
    ) {
        User user = userService.loginDefaultUser(request.toDefaultLoginUser(deviceToken));
        deviceTokenService.setDeviceToken(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add(accessHeaderString, tokenPrefix + jwtTokenUtil.createAccessToken(user));
        headers.add(refreshHeaderString, tokenPrefix + jwtTokenUtil.createRefreshToken(user));
        return HttpResponse.success(DefaultId.of(user.getId().value()));
    }
    @DeleteMapping("/logout")
    public HttpResponse<Object> logOut(
            @RequestAttribute Long userId,
            @RequestHeader String deviceToken
    ){
        deviceTokenService.removeDeviceToken(new User.UserId(userId),deviceToken);
        return HttpResponse.successOnly();
    }
    @PostMapping("/register")
    public HttpResponse<Object> register(
            @RequestBody UserRequest.register request
    ) {
        userService.registUser(request.toDefaultRegisterUser());
        return HttpResponse.successOnly();
    }
    private HttpResponse<DefaultId> handleOAuthLogin(
            User user
    ) {
        User loginedUser = userService.loginOAuthUser(user);
        deviceTokenService.setDeviceToken(loginedUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add(accessHeaderString, tokenPrefix + jwtTokenUtil.createAccessToken(loginedUser));
        headers.add(refreshHeaderString, tokenPrefix + jwtTokenUtil.createRefreshToken(loginedUser));
        return HttpResponse.success(DefaultId.of(loginedUser.getId().value()));
    }
}