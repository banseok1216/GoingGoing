package com.example.user.service;

import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.implementation.UserReader;
import com.example.user.model.UserRoutineWindow;
import com.example.user.service.UserRoutineService;
import com.example.user.implementation.UserAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserRoutineServiceUnitTest {
    @Mock
    private UserAppender userAppender;
    Routine firstRoutine;
    Routine secondRoutine;
    User fakeUser;
    @Mock
    private UserReader userReader;
    @InjectMocks
    private UserRoutineService userRoutineService;

    @BeforeEach
    void setUp() {
        firstRoutine = Routine.withId(new Routine.RoutineId(123L), 1L, "testName1", 1);
        secondRoutine = Routine.withId(new Routine.RoutineId(456L), 2L, "testName2", 2);
        fakeUser =User.withId(new User.UserId(123L), "user1", null, null, null, null);
        MockitoAnnotations.openMocks(this);

    }

    @Test
    @DisplayName("모든 유저 루틴 가져오기")
    public void testGetAllUserRoutine_Success() {
        UserRoutineWindow savedRoutineWindow = new UserRoutineWindow(Arrays.asList(firstRoutine,secondRoutine));
        when(userReader.readUserRoutines(fakeUser.getId())).thenReturn(savedRoutineWindow);
        UserRoutineWindow routineWindow = userRoutineService.getAllUserRoutine(fakeUser);
        assertEquals(routineWindow, savedRoutineWindow);
    }

    @Test
    @DisplayName("유저 루틴 삭제하기")
    void testDeleteUserRoutine_Success() {
        Routine.RoutineId routineId = new Routine.RoutineId(123L);
        UserRoutineWindow savedRoutineWindow = new UserRoutineWindow(Arrays.asList(firstRoutine,secondRoutine));
        when(userReader.readUserRoutines(fakeUser.getId())).thenReturn(savedRoutineWindow);
        userRoutineService.deleteUserRoutine(fakeUser, routineId);
        ArgumentCaptor<UserRoutineWindow> routineWindowCaptor = ArgumentCaptor.forClass(UserRoutineWindow.class);
        verify(userAppender).saveUserRoutines(routineWindowCaptor.capture());
        assertEquals(1, routineWindowCaptor.getValue().getRoutines().get(0).getIndex());
    }

    @Test
    @DisplayName("유저 루틴 만들기")
    void testCreateUserRoutine_Success() {
        Routine.RoutineId inputRoutineId = new Routine.RoutineId(123L);
        Routine routine = firstRoutine;
        when(userAppender.saveUserRoutine(routine, fakeUser)).thenReturn(inputRoutineId);
        Routine.RoutineId routineId = userRoutineService.createUserRoutine(routine, fakeUser);
        assertEquals(inputRoutineId.value(), routineId.value());
    }

}