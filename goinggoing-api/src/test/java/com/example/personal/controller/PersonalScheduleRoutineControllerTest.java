package com.example.personal.controller;

import com.example.personal.PersonalSchedule;
import com.example.personal.dto.PersonalScheduleRequest;
import com.example.personal.dto.ScheduleRoutineRequest;
import com.example.personal.service.PersonalScheduleRoutineService;
import com.example.routine.Routine;
import com.example.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonalScheduleRoutineController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PersonalScheduleRoutineControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonalScheduleRoutineService personalScheduleRoutineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testModifyPersonalScheduleRoutine() throws Exception {
        ArgumentCaptor<Routine> argumentCaptor = ArgumentCaptor.forClass(Routine.class);
        mockMvc.perform(put("/api/v2/routineSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new ScheduleRoutineRequest.Update(123L,"testName",60L,5)))
                        .param("personalScheduleId","456"))
                .andExpect(status().isOk());
        verify(personalScheduleRoutineService).modifyScheduleRoutine(
                eq(new PersonalSchedule.PersonalScheduleId(456L)), argumentCaptor.capture());
        assertEquals(123L, argumentCaptor.getValue().getRoutineId().value());
        assertEquals("testName", argumentCaptor.getValue().getRoutineName());
        assertEquals(60L, argumentCaptor.getValue().getRoutineTime());
        assertEquals(5, argumentCaptor.getValue().getIndex());
    }

    @Test
    public void testCreatePersonalScheduleRoutine() throws Exception {
        ArgumentCaptor<Routine> argumentCaptor = ArgumentCaptor.forClass(Routine.class);
        mockMvc.perform(post("/api/v2/routineSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new ScheduleRoutineRequest.Create("testName",60L,5)))
                        .param("personalScheduleId","456"))
                .andExpect(status().isOk());
        verify(personalScheduleRoutineService).createScheduleRoutine(
                eq(new PersonalSchedule.PersonalScheduleId(456L)), argumentCaptor.capture());
        assertNull(argumentCaptor.getValue().getRoutineId());
        assertEquals("testName", argumentCaptor.getValue().getRoutineName());
        assertEquals(60L, argumentCaptor.getValue().getRoutineTime());
        assertEquals(5, argumentCaptor.getValue().getIndex());
    }
    @Test
    public void testDeletePersonalScheduleRoutine() throws Exception {
        mockMvc.perform(delete("/api/v2/routineSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new ScheduleRoutineRequest.Create("testName",60L,5)))
                        .param("personalScheduleId","456")
                    .param("scheduleRoutineId","123"))
                .andExpect(status().isOk());
        verify(personalScheduleRoutineService).deleteScheduleRoutine(
                new PersonalSchedule.PersonalScheduleId(456L),
                new Routine.RoutineId(123L));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
