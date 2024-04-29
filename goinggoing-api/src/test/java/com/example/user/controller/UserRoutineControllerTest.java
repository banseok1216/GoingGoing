package com.example.user.controller;

import com.example.group.GroupSchedule;
import com.example.redis.device.service.DeviceTokenService;
import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
import com.example.user.User;
import com.example.user.service.UserRoutineService;
import com.example.user.service.UserService;
import com.example.utils.jwt.JwtTokenUtil;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@WebMvcTest(UserRoutineController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserRoutineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRoutineService userRoutineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUserRoutine() throws Exception {
        List<Routine> routineList = Arrays.asList(
                Routine.withId(new Routine.RoutineId(123L), 1L, "testName1", 1),
                Routine.withId(new Routine.RoutineId(456L), 2L, "testName2", 2)
        );
        RoutineWindow routineWindow = new RoutineWindow(routineList);
        Mockito.when(userRoutineService.getAllUserRoutine(new User.UserId(123L))).thenReturn(routineWindow);
        mockMvc.perform(get("/api/v2/userRoutine")
                        .requestAttr("userId", 123L))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data[0].routineId").value("123"))
                .andExpect(jsonPath("$.data[0].routineTime").value("1"))
                .andExpect(jsonPath("$.data[0].routineName").value("testName1"))
                .andExpect(jsonPath("$.data[0].index").value("1"))
                .andExpect(status().isOk());
        verify(userRoutineService).getAllUserRoutine(new User.UserId(123L));
    }
    @Test
    public void testDeleteUserRoutine() throws Exception {
        mockMvc.perform(delete("/api/v2/userRoutine")
                        .requestAttr("userId", 123L)
                        .param("userRoutineId","456"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(status().isOk());
        verify(userRoutineService).deleteUserRoutine(new User.UserId(123L),new Routine.RoutineId(456L));
    }
    @Test
    public void testCreateUserRoutine() throws Exception {
        ArgumentCaptor<Routine> argumentCaptor = ArgumentCaptor.forClass(Routine.class);
        Mockito.when(userRoutineService.createUserRoutine(
                        argumentCaptor.capture(),
                        Mockito.eq(new User.UserId(123L))))
                .thenReturn(new Routine.RoutineId(456L));
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
                Mockito.eq(new User.UserId(123L)));
        Routine capturedRoutine = argumentCaptor.getValue();
        assertEquals(123L, capturedRoutine.getRoutineTime());
        assertEquals("testName", capturedRoutine.getRoutineName());
        assertEquals(1, capturedRoutine.getIndex());
    }
}
