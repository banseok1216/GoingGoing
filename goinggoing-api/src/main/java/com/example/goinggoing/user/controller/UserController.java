package com.example.goinggoing.user.controller;

import com.example.goinggoing.redis.device.service.DeviceTokenService;
import com.example.goinggoing.user.mapstruct.UserMapper;
import com.example.goinggoing.user.dto.UserRequestDto;
import com.example.goinggoing.user.dto.UserResponseDto;
import com.example.goinggoing.user.service.UserService;
import com.example.goinggoing.utils.jwt.JwtTokenUtil;
import com.example.goinggoingdomain.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController //(1)
@RequestMapping("/api")
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

    @Autowired
    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil, DeviceTokenService deviceTokenService) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.deviceTokenService = deviceTokenService;
    }

    @PostMapping("/auth/kakao")
    public ResponseEntity<String> postKakaoLogin(@RequestBody UserRequestDto userRequestDto, HttpServletRequest request) {
        String deviceToken = request.getHeader("device_token");
        User user = UserMapper.INSTANCE.toEntity(userRequestDto);
        User loginedKakaoUser = userService.loginKakaoUser(user);
        String accessToken= jwtTokenUtil.createAccessToken(loginedKakaoUser);
        String refreshToken= jwtTokenUtil.createRefreshToken(loginedKakaoUser);
        deviceTokenService.setDeviceToken(String.valueOf(loginedKakaoUser.getUserId()),deviceToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add(accessHeaderString, tokenPrefix + accessToken);
        headers.add(refreshHeaderString, tokenPrefix + refreshToken);
        return ResponseEntity.ok().headers(headers).body("success");
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUser(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.getUser(userId);
        UserResponseDto responseDto = UserMapper.INSTANCE.toUserResponseDto(user);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> postLogin(@RequestBody UserRequestDto loginRequestDto, HttpServletRequest request) {
        String deviceToken = request.getHeader("device_token");
        User user = UserMapper.INSTANCE.toEntity(loginRequestDto);
        User loginedDefaultUser = userService.loginDefaultUser(user);

        if(loginedDefaultUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 잘못되었습니다.");
        }

        String accessToken= jwtTokenUtil.createAccessToken(loginedDefaultUser);
        String refreshToken= jwtTokenUtil.createRefreshToken(loginedDefaultUser);

        if (accessToken == null || refreshToken == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("토큰 생성에 실패했습니다.");
        }
        deviceTokenService.setDeviceToken(String.valueOf(loginedDefaultUser.getUserId()),deviceToken);
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
    public ResponseEntity<Object> postRegister(@RequestBody UserRequestDto createUserRequest) {
        User user = UserMapper.INSTANCE.toEntity(createUserRequest);
        userService.registerUser(user);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body("success");
    }

}