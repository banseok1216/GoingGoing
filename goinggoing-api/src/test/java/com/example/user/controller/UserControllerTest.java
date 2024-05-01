package com.example.user.controller;
import com.example.user.domain.User;
import com.example.user.service.UserService;

import com.example.utils.jwt.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post("/api/v2/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userEmail\": \"test@example.com\", \"password\": \"testPassword\"}"))
                .andExpect(status().isOk());
        verify(userService, times(1)).registerUser(argThat(user ->
                user.getUserEmail().equals("test@example.com") &&
                        user.getPassword().password().equals("testPassword")
        ));
    }
    @Test
    public void testKakaoLogin() throws Exception {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        User user = User.withId(new User.UserId(123L), null, null, null, null, null);
        when(userService.loginOAuthUser(any(User.class))).thenReturn(user);
        when(jwtTokenUtil.createAccessToken(user)).thenReturn("mockedAccessToken");
        when(jwtTokenUtil.createRefreshToken(user)).thenReturn("mockedRefreshToken");
        mockMvc.perform(post("/api/v2/auth/kakao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("deviceToken", "mockedDeviceToken")
                        .content("{\"userEmail\": \"testEmail\", \"userNickname\": \"testNickname\"}"))
                .andExpect(jsonPath("$.data.id").value(user.getId().value()))
                .andExpect(status().isOk());
        verify(userService).loginOAuthUser(userCaptor.capture());
        assertEquals(User.UserType.OAUTH_KAKAO, userCaptor.getValue().getUserType());
    }
    @Test
    public void testGoogleLogin() throws Exception {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        User user = User.withId(new User.UserId(123L), null, null, null, null, null);
        when(userService.loginOAuthUser(any(User.class))).thenReturn(user);
        when(jwtTokenUtil.createAccessToken(user)).thenReturn("mockedAccessToken");
        when(jwtTokenUtil.createRefreshToken(user)).thenReturn("mockedRefreshToken");
        mockMvc.perform(post("/api/v2/auth/google")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("deviceToken", "mockedDeviceToken")
                        .content("{\"userEmail\": \"testEmail\", \"userNickname\": \"testNickname\"}"))
                .andExpect(jsonPath("$.data.id").value(user.getId().value()))
                .andExpect(status().isOk());
        verify(userService).loginOAuthUser(userCaptor.capture());
        assertEquals(User.UserType.OAUTH_GOOGLE, userCaptor.getValue().getUserType());
    }
    @Test
    public void testLogOut() throws Exception {
        long userId = 123;
        String deviceToken = "device_token_value";
        mockMvc.perform(delete("/api/v2/logout")
                        .requestAttr("userId", String.valueOf(userId))
                        .header("deviceToken", deviceToken))
                .andExpect(status().isOk());
    }
    @Test
    public void testLogin() throws Exception {
        User user = User.withId(new User.UserId(123L),null,null,null,null,null);
        when(userService.loginDefaultUser(any(User.class))).thenReturn(user);
        when(jwtTokenUtil.createAccessToken(user)).thenReturn("mockedAccessToken");
        when(jwtTokenUtil.createRefreshToken(user)).thenReturn("mockedRefreshToken");
        mockMvc.perform(post("/api/v2/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("deviceToken", "mockedDeviceToken")
                        .content("{\"username\": \"test\", \"password\": \"test123\"}"))
                .andExpect(jsonPath("$.data.id").value(user.getId().value()))
                .andExpect(status().isOk());
        verify(userService).loginDefaultUser(any(User.class));
    }
    @Test
    public void testGetUser() throws Exception {
        Long userId = 123L;
        User user = User.withId(new User.UserId(userId), "test", "test@example.com",null,null,null);
        when(userService.getUser(new User.UserId(userId))).thenReturn(user);
        mockMvc.perform(get("/api/v2/user")
                        .requestAttr("userId", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.userId").value(user.getId().value()))
                .andExpect(jsonPath("$.data.userNickname").value(user.getUserNickname()))
                .andExpect(jsonPath("$.data.userEmail").value(user.getUserEmail()));
        verify(userService).getUser(any(User.UserId.class));
    }
}
