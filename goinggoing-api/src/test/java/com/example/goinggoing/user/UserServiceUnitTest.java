package com.example.goinggoing.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import com.example.goinggoing.user.service.UserService;
import com.example.goinggoingdomain.domain.user.User;
import com.example.goinggoingdomain.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

public class UserServiceUnitTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Captor
    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);


    @Test
    @DisplayName("카카오 유저 로그인")
    public void testLoginKakaoUser() {
        String userEmail="testEmail";
        String userNickname = "testNickname";
        User fakeUser = User.builder().userNickname(userNickname).userEmail(userEmail).build();
        when(userRepository.findByUserEmail(fakeUser.getUserEmail())).thenReturn(null);
        userService.loginKakaoUser(fakeUser);
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
        User savedUser = userArgumentCaptor.getValue();
        assertEquals(savedUser.getUserNickname(), userNickname);
        assertEquals(savedUser.getUserRole(), "user");
        assertEquals(savedUser.getUserType(), "kakao");
        assertEquals(savedUser.getUserEmail(), userEmail);
    }
    @Test
    @DisplayName("일반 유저 로그인 - 실패")
    public void testLoginDefaultUser_Failure() {
        String userEmail="testEmail";
        String password = "testPassword";
        String wrongPassword = "wrongPassword";
        User savedUser = User.builder().userEmail(userEmail).password(password).build();
        User fakeUser = User.builder().userEmail(userEmail).password(wrongPassword).build();
        when(userRepository.findByUserEmail(fakeUser.getUserEmail())).thenReturn(savedUser);
        when(passwordEncoder.matches(fakeUser.getPassword(), savedUser.getPassword())).thenReturn(false);
        User loggedInUser = userService.loginDefaultUser(fakeUser);
        assertNull(loggedInUser);
    }

    @Test
    @DisplayName("일반 유저 로그인 - 성공")
    public void testLoginDefaultUser_Success() {
        String userEmail = "testEmail";
        String password = "testPassword";
        User savedUser = User.builder().userEmail(userEmail).password(password).build();
        User fakeUser = User.builder().userEmail(userEmail).password(password).build();
        when(userRepository.findByUserEmail(fakeUser.getUserEmail())).thenReturn(savedUser);
        when(passwordEncoder.matches(fakeUser.getPassword(), savedUser.getPassword())).thenReturn(true);
        User loggedInUser = userService.loginDefaultUser(fakeUser);
        assertEquals(fakeUser, loggedInUser);
    }

    @Test
    @DisplayName("특정 ID에 해당하는 사용자 가져오기")
    public void testGetUserById() {
        Long userId = 1L;
        User user = User.builder().userId(userId).build();
        when(userRepository.findByUserId(userId)).thenReturn(user);
        User retrievedUser = userService.getUser(userId);
        assertEquals(user, retrievedUser);
        verify(userRepository, times(1)).findByUserId(userId);
    }
    @Test
    @DisplayName("새로운 사용자 등록 - 성공")
    public void testRegisterUser_Success() {
        String userEmail = "test@example.com";
        String password = "testPassword";
        User userRegister = User.builder().userEmail(userEmail).password(password).build();
        when(userRepository.findByUserEmail(userEmail)).thenReturn(null);
        when(passwordEncoder.encode(password)).thenReturn("hashedPassword");
        User registeredUser = userService.registerUser(userRegister);
        assertEquals(userEmail, registeredUser.getUserEmail());
        assertEquals("hashedPassword", registeredUser.getPassword());
        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(userEmail, capturedUser.getUserEmail());
        assertEquals("hashedPassword", capturedUser.getPassword());
    }
    @Test
    @DisplayName("새로운 사용자 등록 - 이미 등록된 사용자")
    public void testRegisterUser_UserAlreadyExists() {
        String userEmail = "test@example.com";
        User userRegister = User.builder().userEmail(userEmail).build();
        when(userRepository.findByUserEmail(userEmail)).thenReturn(userRegister);
        User registeredUser = userService.registerUser(userRegister);
        assertNull(registeredUser);
        verify(userRepository, never()).save(any(User.class));
    }
}
