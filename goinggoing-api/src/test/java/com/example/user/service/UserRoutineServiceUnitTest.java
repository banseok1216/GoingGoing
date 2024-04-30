package com.example.user.service;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
import com.example.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserRoutineServiceUnitTest {
    @Mock
    private UserWriter userWriter;
    @Mock
    private UserReader userReader;
    @InjectMocks
    private UserRoutineService userRoutineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("모든 유저 루틴 가져오기")
    public void testGetAllUserRoutine_Success() {
        User user = createUser();
        RoutineWindow savedRoutineWindow = createRoutineWindow();
        when(userReader.readUserRoutines(user.getId())).thenReturn(savedRoutineWindow);
        RoutineWindow routineWindow = userRoutineService.getAllUserRoutine(user);
        assertEquals(routineWindow, savedRoutineWindow);
    }

    @Test
    @DisplayName("유저 루틴 삭제하기")
    void testDeleteUserRoutine_Success() {
        Routine.RoutineId routineId = new Routine.RoutineId(123L);
        User savedUser = createUser();
        RoutineWindow savedRoutineWindow = createRoutineWindow();
        when(userReader.readUserRoutines(savedUser.getId())).thenReturn(savedRoutineWindow);
        userRoutineService.deleteUserRoutine(savedUser, routineId);
        ArgumentCaptor<RoutineWindow> routineWindowCaptor = ArgumentCaptor.forClass(RoutineWindow.class);
        verify(userWriter).saveUserRoutines(routineWindowCaptor.capture());
        assertEquals(1, routineWindowCaptor.getValue().getRoutines().get(0).getIndex());
    }

    @Test
    @DisplayName("유저 루틴 만들기")
    void testCreateUserRoutine_Success() {
        Routine.RoutineId inputRoutineId = new Routine.RoutineId(123L);
        User savedUser = createUser();
        Routine routine = createRoutine();
        when(userWriter.saveUserRoutine(routine, savedUser)).thenReturn(inputRoutineId);
        Routine.RoutineId routineId = userRoutineService.createUserRoutine(routine, savedUser);
        assertEquals(inputRoutineId.value(), routineId.value());
    }

    public User createUser() {
        return User.withId(new User.UserId(123L), null, null, null, null, null);
    }

    public RoutineWindow createRoutineWindow() {
        return new RoutineWindow(Arrays.asList(
                Routine.withId(new Routine.RoutineId(123L), 1L, "testName1", 1),
                Routine.withId(new Routine.RoutineId(456L), 2L, "testName2", 2)
        ));
    }

    public Routine createRoutine() {
        return Routine.withId(new Routine.RoutineId(123L), 1L, "testName1", 1);
    }
}