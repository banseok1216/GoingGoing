package com.example.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.user.model.User;
import com.example.user.implementation.UserCachedHandler;
import com.example.user.implementation.UserChecker;
import com.example.user.implementation.UserReader;
import com.example.user.implementation.UserAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.when;

public class
UserServiceUnitTest {
    User fakeUser;
    @Mock
    private UserChecker userChecker;
    @Mock
    private UserAppender userAppender;
    @Mock
    private UserReader userReader;
    @Mock
    private UserCachedHandler userCachedHandler;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fakeUser =User.withId(new User.UserId(123L), "user1", null, null,  new User.Password("testPaasword"), null);
    }
    @Captor
    ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
    @Test
    @DisplayName("소셜 유저 새롭게 로그인 성공")
    public void testLoginOAuthUser_NewUser() {
        when(userChecker.isNotExist(any(User.class))).thenReturn(true);
        when(userAppender.saveUser(any(User.class))).thenReturn(fakeUser.getId());
        when(userReader.readUser(fakeUser.getId())).thenReturn(fakeUser);
        User resultUser = userService.loginOAuthUser(fakeUser);
        assertEquals(fakeUser, resultUser);
    }

    @Test
    @DisplayName("소셜 유저 기존 로그인 성공")
    public void testLoginOAuthUser_ExistingUser() {
        when(userChecker.isNotExist(any(User.class))).thenReturn(false);
        when(userReader.readUserByEmail(any(User.class))).thenReturn(fakeUser);
        User resultUser = userService.loginOAuthUser(fakeUser);
        assertEquals(fakeUser, resultUser);
    }
    @Test
    @DisplayName("일반 유저 로그인 성공")
    public void testLoginDefaultUser_Success() {
        User user = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.LOCAL,new User.Password("testPassword").hashPassword(),"testDeviceToken");
        User userLogin = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.LOCAL,new User.Password("testPassword"),"testDeviceToken");
        when(userReader.readUser(any(User.UserId.class))).thenReturn(user);
        User result = userService.loginLocalUser(userLogin);
        assertEquals(user, result);
    }
    @Test
    @DisplayName("일반 유저 로그인 실패 - 틀린 비밀번호")
    public void testLoginDefaultUser_Fail_WrongPassword() {
        User user = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.LOCAL,new User.Password("wrongPassword"),"testDeviceToken");
        User userLogin = User.withId(new User.UserId(123L),"testName","testEmail", User.UserType.LOCAL,new User.Password("testPassword"),"testDeviceToken");
        when(userReader.readUser(userLogin.getId())).thenReturn(user);
        BusinessException exception = assertThrows(BusinessException.class, () -> userService.loginLocalUser(userLogin));
        assertEquals(ErrorCode.USER_LOGIN_PASSWORD_FAIL, exception.getErrorCode());
    }
    @Test
    @DisplayName("일반 유저 회원가입")
    public void testRegisterUser() {
        userService.registerUser(fakeUser);
        verify(userAppender).saveUser(argumentCaptor.capture());
        assertEquals(fakeUser.getPassword().hashPassword(), argumentCaptor.getValue().getPassword());
    }
    @Test
    @DisplayName("유저가 캐시에 존재")
    void testGetUserFromCache() {
        when(userCachedHandler.get("123")).thenReturn(fakeUser);
        verify(userReader, never()).readUser(any());
        verify(userCachedHandler, never()).put(any(),any());
    }
    @Test
    @DisplayName("유저가 캐시에 존재하지 않음")
    void testGetUserFromDatabase() {
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(userReader.readUser(new User.UserId(123L))).thenReturn(fakeUser);
        User resultUser = userService.getUser(new User.UserId(123L));
        verify(userCachedHandler).put(stringArgumentCaptor.capture(), eq(fakeUser));
        assertEquals("123", stringArgumentCaptor.getValue());
        assertEquals(fakeUser, resultUser);
    }
}
