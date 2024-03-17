package com.example.goinggoing.user;

import com.example.goinggoing.redis.device.service.DeviceTokenService;
import com.example.goinggoing.user.controller.UserController;
import com.example.goinggoing.user.dto.UserResponseDto;
import com.example.goinggoing.user.service.UserService;
import com.example.goinggoing.utils.jwt.JwtTokenUtil;
import com.example.goinggoingdomain.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
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

    @MockBean
    private DeviceTokenService deviceTokenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostKakaoLogin() throws Exception {
        User fakeUser = User.builder().userId(1L).userNickname("testUser").userEmail("testEmail").build();
        String fakeAccessToken = "fakeAccessToken";
        String fakeRefreshToken = "fakeRefreshToken";
        when(userService.loginKakaoUser(any(User.class)))
                .thenReturn(fakeUser);
        when(jwtTokenUtil.createAccessToken(fakeUser)).
                thenReturn(fakeAccessToken);
        when(jwtTokenUtil.createRefreshToken(fakeUser)).
                thenReturn(fakeRefreshToken);
        mockMvc.perform(post("/api/auth/kakao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userEmail\": \"test@example.com\", \"userNickname\": \"testUser\"}")
                        .header("device_token", "testDeviceToken"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().exists("access_token"))
                .andExpect(MockMvcResultMatchers.header().exists("refresh_token"))
                .andExpect(MockMvcResultMatchers.content().string("success"));
        verify(userService, times(1)).loginKakaoUser(any(User.class));
        verify(jwtTokenUtil,times(1)).createRefreshToken(fakeUser);
        verify(jwtTokenUtil,times(1)).createAccessToken(fakeUser);
        verify(deviceTokenService, times(1)).setDeviceToken("1", "testDeviceToken");
    }


    @Test
    public void testGetUser() throws Exception {
        User fakeUser = createFakeUser();
        when(userService.getUser(1L)).thenReturn(fakeUser);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserEmail("testUserEmail");
        userResponseDto.setUserNickname("testUserNickname");
        userResponseDto.setUserId(1L);
        ResultActions resultActions = mockMvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .requestAttr("userId",1L))
                .andExpect(status().isOk());
        verify(userService, times(1)).getUser(1L);
        resultActions.andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userEmail").exists())
                .andExpect(jsonPath("$.userNickname").exists());
    }

    @Test
    public void testPostLogin() throws Exception {
        User fakeUser = User.builder().userId(1L).userNickname("testUser").userEmail("testEmail").build();
        String fakeAccessToken = "fakeAccessToken";
        String fakeRefreshToken = "fakeRefreshToken";
        when(jwtTokenUtil.createAccessToken(fakeUser)).
                thenReturn(fakeAccessToken);
        when(jwtTokenUtil.createRefreshToken(fakeUser)).
                thenReturn(fakeRefreshToken);
        when(userService.loginDefaultUser(Mockito.any(User.class)))
                .thenReturn(fakeUser);
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userEmail\": \"test@example.com\", \"password\": \"testPassword\"}")
                        .header("device_token", "testDeviceToken"))
                .andExpect(status().isOk())
                .andExpect(header().exists("access_token"))
                .andExpect(header().exists("refresh_token"))
                .andExpect(content().string("success"));
        verify(jwtTokenUtil,times(1)).createRefreshToken(fakeUser);
        verify(jwtTokenUtil,times(1)).createAccessToken(fakeUser);
        verify(userService, times(1)).loginDefaultUser(any(User.class));
    }
    @Test
    public void testPostRegister() throws Exception {
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userEmail\": \"test@example.com\", \"password\": \"testPassword\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
        verify(userService, times(1)).registerUser(any(User.class));
    }

    @Test
    public void testLogOut() throws Exception {
        mockMvc.perform(delete("/api/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("device_token", "testDeviceToken")
                        .requestAttr("userId",1L))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
        verify(deviceTokenService, times(1)).removeDeviceToken("1","testDeviceToken");
    }

    private User createFakeUser() {
        return User.builder()
                .userEmail("testUserEmail")
                .userNickname("testUserNickname")
                .userRole("testUserRole")
                .userType("testUserType")
                .userId(1L)
                .password("testPassword")
                .build();
    }

}
