package com.example.user.controller;
import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.model.UserRoutineWindow;
import com.example.user.service.UserRoutineService;
import com.example.user.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRoutineController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserRoutineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRoutineService userRoutineService;
    @MockBean
    private UserService userService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUserRoutine() throws Exception {
        UserRoutineWindow routineWindow = new UserRoutineWindow(Arrays.asList(
                Routine.withId(new Routine.RoutineId(123L), 1L, "testName1", 1),
                Routine.withId(new Routine.RoutineId(456L), 2L, "testName2", 2)
        ));
        User user = User.withId(new User.UserId(123L), null, null, null, null, null);
        when(userRoutineService.getAllUserRoutine(user)).thenReturn(routineWindow);
        when(userService.getUser(user.getId())).thenReturn(user);
        mockMvc.perform(get("/api/v2/userRoutine")
                        .requestAttr("userId", 123L))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data[0].routineId").value("123"))
                .andExpect(jsonPath("$.data[0].routineTime").value("1"))
                .andExpect(jsonPath("$.data[0].routineName").value("testName1"))
                .andExpect(jsonPath("$.data[0].index").value("1"))
                .andExpect(status().isOk());
        verify(userRoutineService).getAllUserRoutine(user);
    }
    @Test
    public void testDeleteUserRoutine() throws Exception {
        User user = User.withId(new User.UserId(123L), null, null, null, null, null);
        when(userService.getUser(user.getId())).thenReturn(user);
        mockMvc.perform(delete("/api/v2/userRoutine")
                        .requestAttr("userId", 123L)
                        .param("userRoutineId","456"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(status().isOk());
        verify(userRoutineService).deleteUserRoutine(user,new Routine.RoutineId(456L));
    }
    @Test
    public void testCreateUserRoutine() throws Exception {
        ArgumentCaptor<Routine> argumentCaptor = ArgumentCaptor.forClass(Routine.class);
        User user = User.withId(new User.UserId(123L), null, null, null, null, null);
        when(userRoutineService.createUserRoutine(
                        argumentCaptor.capture(),
                        Mockito.eq(user)))
                .thenReturn(new Routine.RoutineId(456L));
        when(userService.getUser(user.getId())).thenReturn(user);
        mockMvc.perform(post("/api/v2/userRoutine")
                        .requestAttr("userId", 123L)
                        .param("userRoutineId", "456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"routineTime\": \"123\"," +
                                "\"routineName\": \"testName\"," +
                                "\"index\": \"1\"}"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.id").value(456L))
                .andExpect(status().isOk());
        verify(userRoutineService).createUserRoutine(
                argumentCaptor.capture(),
                Mockito.eq(user));
        Routine capturedRoutine = argumentCaptor.getValue();
        assertEquals(123L, capturedRoutine.getRoutineTime());
        assertEquals("testName", capturedRoutine.getRoutineName());
        assertEquals(1, capturedRoutine.getIndex());
    }
}
