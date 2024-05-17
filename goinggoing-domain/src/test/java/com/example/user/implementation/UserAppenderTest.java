package com.example.user.implementation;

import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.model.UserRoutineWindow;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserAppenderTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    UserAppender userAppender;
    User fakeUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fakeUser =User.withId(new User.UserId(123L), "user1", null, null,  new User.Password("testPaasword"), null);
    }

    @Test
    public void 유저_저장_성공_테스트() {
        when(userRepository.saveUser(any(User.class))).thenReturn(new User.UserId(123L));
        userAppender.saveUser(fakeUser);
        verify(userRepository).saveUser(any(User.class));
    }

    @Test
    public void 유저_루틴_저장_테스트() {
        when(userRepository.saveUserRoutine(any(), any())).thenReturn(new Routine.RoutineId(123L));
        Routine.RoutineId routineId = userAppender.saveUserRoutine(any(Routine.class),any(User.class));
        assertEquals(routineId.value(), 123L);
    }

    @Test
    public void 유저_루틴_리스트_저장_테스트() {
        UserRoutineWindow routineWindow = new UserRoutineWindow(new ArrayList<>());
        userAppender.saveUserRoutines(routineWindow);
        verify(userRepository).saveUserRoutines(any(UserRoutineWindow.class));
    }
}
