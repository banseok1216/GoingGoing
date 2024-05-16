package com.example.personal.controller;

import com.example.personal.model.PersonalSchedule;
import com.example.personal.dto.PersonalScheduleRequest;
import com.example.personal.service.PersonalScheduleService;
import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonalScheduleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PersonalScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonalScheduleService personalScheduleService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetPersonalSchedule() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        RoutineWindow routineWindow = new RoutineWindow(Arrays.asList(
                Routine.withId(new Routine.RoutineId(123L), 1L, "testName1", 1),
                Routine.withId(new Routine.RoutineId(456L), 2L, "testName2", 2)
        ));
        PersonalSchedule personalSchedule = PersonalSchedule.withId(
                new PersonalSchedule.PersonalScheduleId(123L),
                5,
                new PersonalSchedule.PersonalScheduleTime(dateTime.minusHours(1),dateTime),
                new PersonalSchedule.PersonalScheduleStatus(false,true),
                routineWindow, null,null);
        when(personalScheduleService.loadPersonalSchedule(new PersonalSchedule.PersonalScheduleId(123L))).thenReturn(personalSchedule);
        mockMvc.perform(get("/api/v2/personal/schedule")
                        .param("personalScheduleId","123"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.personalScheduleId").value(123L))
                .andExpect(jsonPath("$.data.duration").value(5))
                .andExpect(jsonPath("$.data.scheduleStart").value(false))
                .andExpect(jsonPath("$.data.scheduleDone").value(true))
                .andExpect(jsonPath("$.data.routineResponseList[0].scheduleRoutineId").value(123L))
                .andExpect(jsonPath("$.data.routineResponseList[0].scheduleRoutineName").value("testName1"))
                .andExpect(jsonPath("$.data.routineResponseList[0].scheduleRoutineTime").value(1L))
                .andExpect(jsonPath("$.data.routineResponseList[0].index").value(1))
                .andExpect(status().isOk());
    }
    @Test
    void testModifyPersonalSchedule() throws Exception {
        ArgumentCaptor<PersonalSchedule> argumentCaptor = ArgumentCaptor.forClass(PersonalSchedule.class);
        mockMvc.perform(put("/api/v2/personal/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createFakeScheduleRequest())))
                .andExpect(status().isOk());
        verify(personalScheduleService).modifyPersonalSchedule(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue().getId().value(),123L);
        assertEquals(argumentCaptor.getValue().getPersonalDuration(),60);
        assertNull(argumentCaptor.getValue().getPersonalScheduleSend());
        assertNull(argumentCaptor.getValue().getPersonalScheduleTime());
        assertNull(argumentCaptor.getValue().getPersonalScheduleStatus());
        assertNull(argumentCaptor.getValue().getScheduleRoutineWindow());
    }
    private PersonalScheduleRequest createFakeScheduleRequest() {
        return new PersonalScheduleRequest(60,123L);
    }

    private String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
