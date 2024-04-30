package com.example.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.when;

public class UserServiceUnitTest {
    @Mock
    private UserChecker userChecker;
    @Mock
    private UserWriter userWriter;
    @Mock
    private UserReader userReader;
    @Mock
    private UserCachedReader userCachedReader;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Captor
    ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

    @Test
    @DisplayName("카카오,구글 유저 로그인")
    public void testLoginKakaoUser() {
        User userLogin = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.OAUTH_KAKAO,null,"testDeviceToken");
        when(userWriter.saveUser(userLogin)).thenReturn(new User.UserId(123L));
        when(userReader.readUser(any(User.UserId.class))).thenReturn(userLogin);
        User result = userService.loginOAuthUser(userLogin);
        assertEquals(userLogin, result);
        verify(userChecker, times(1)).isDuplicate(userLogin);
        verify(userWriter, times(1)).saveUser(userLogin);
    }
    @Test
    @DisplayName("일반 유저 로그인 성공")
    public void testLoginDefaultUser_Success() {
        User user = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.OAUTH_DEFAULT,new User.Password("testPassword").hashPassword(),"testDeviceToken");
        User userLogin = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.OAUTH_DEFAULT,new User.Password("testPassword"),"testDeviceToken");
        when(userReader.readUser(any(User.UserId.class))).thenReturn(user);
        User result = userService.loginDefaultUser(userLogin);
        assertEquals(user, result);
    }
    @Test
    @DisplayName("일반 유저 로그인 실패 - 틀린 비밀번호")
    public void testLoginDefaultUser_Fail_WrongPassword() {
        User user = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.OAUTH_DEFAULT,new User.Password("wrongPassword"),"testDeviceToken");
        User userLogin = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.OAUTH_DEFAULT,new User.Password("testPassword"),"testDeviceToken");
        when(userReader.readUser(userLogin.getId())).thenReturn(user);
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.loginDefaultUser(userLogin));
        assertEquals(ErrorCode.USER_LOGIN_PASSWORD_FAIL, exception.getErrorCode());
    }
    @Test
    @DisplayName("일반 유저 회원가입")
    public void testRegisterUser() {
        User user = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.OAUTH_DEFAULT,new User.Password("testPassword"),"testDeviceToken");
        userService.registerUser(user);
        verify(userWriter).saveUser(argumentCaptor.capture());
        assertEquals(user.getPassword().hashPassword(), argumentCaptor.getValue().getPassword());
    }
    @Test
    @DisplayName("유저가 캐시에 존재")
    void testGetUserFromCache() {
        User user = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.OAUTH_DEFAULT,new User.Password("testPassword"),"testDeviceToken");
        when(userCachedReader.get("123")).thenReturn(user);
        verify(userReader, never()).readUser(any());
        verify(userCachedReader, never()).put(any(),any());
    }
    @Test
    @DisplayName("유저가 캐시에 존재하지 않음")
    void testGetUserFromDatabase() {
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        User databaseUser = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.OAUTH_DEFAULT,new User.Password("testPassword"),"testDeviceToken");
        when(userReader.readUser(new User.UserId(123L))).thenReturn(databaseUser);
        User resultUser = userService.getUser(new User.UserId(123L));
        verify(userCachedReader).put(stringArgumentCaptor.capture(), eq(databaseUser));
        assertEquals("123", stringArgumentCaptor.getValue());
        assertEquals(databaseUser, resultUser);
    }
}
