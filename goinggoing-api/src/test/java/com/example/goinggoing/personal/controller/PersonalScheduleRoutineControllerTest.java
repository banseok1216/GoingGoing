//package com.example.goinggoing.personal.controller;
//
//import com.example.personal.dto.PersonalScheduleRoutineRequestDto;
//import com.example.goinggoing.personal.service.PersonalScheduleRoutineService;
//import com.example.goinggoingdomain.domain.personal.PersonalScheduleRoutine;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(PersonalScheduleRoutineController.class)
//@AutoConfigureMockMvc(addFilters = false)
//public class PersonalScheduleRoutineControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PersonalScheduleRoutineService personalScheduleRoutineService;
//
//    @Captor
//    ArgumentCaptor<PersonalScheduleRoutine> scheduleRoutineArgumentCaptor = ArgumentCaptor.forClass(PersonalScheduleRoutine.class);
//
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testUpdatePersonalScheduleRoutine() throws Exception {
//        PersonalScheduleRoutineRequestDto.updateRoutineDto updateRoutineDto = new PersonalScheduleRoutineRequestDto.updateRoutineDto();
//        updateRoutineDto.setScheduleRoutineName("testName");
//        updateRoutineDto.setScheduleRoutineId(123L);
//        updateRoutineDto.setScheduleRoutineIndex(1);
//        updateRoutineDto.setScheduleRoutineTime(5L);
//        mockMvc.perform(put("/api/routineSchedule")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(updateRoutineDto)))
//                .andExpect(status().isOk());
//        verify(personalScheduleRoutineService).updateScheduleRoutine(scheduleRoutineArgumentCaptor.capture());
//        PersonalScheduleRoutine capturedPersonalScheduleRoutine = scheduleRoutineArgumentCaptor.getValue();
//        assertEquals(capturedPersonalScheduleRoutine.getScheduleRoutineName(), updateRoutineDto.getScheduleRoutineName());
//        assertEquals(capturedPersonalScheduleRoutine.getScheduleRoutineId(), updateRoutineDto.getScheduleRoutineId());
//        assertEquals(capturedPersonalScheduleRoutine.getScheduleRoutineIndex(), updateRoutineDto.getScheduleRoutineIndex());
//        assertEquals(capturedPersonalScheduleRoutine.getScheduleRoutineTime(), updateRoutineDto.getScheduleRoutineTime());
//    }
//
//    @Test
//    public void testCreatePersonalScheduleRoutine() throws Exception {
//        PersonalScheduleRoutineRequestDto.createRoutineDto createRoutineDto = new PersonalScheduleRoutineRequestDto.createRoutineDto();
//        createRoutineDto.setScheduleRoutineName("testName");
//        createRoutineDto.setScheduleRoutineIndex(1);
//        createRoutineDto.setScheduleRoutineTime(5L);
//        createRoutineDto.setPersonalScheduleId(456L);
//        mockMvc.perform(post("/api/routineSchedule")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(createRoutineDto)))
//                .andExpect(status().isOk());
//        verify(personalScheduleRoutineService).createScheduleRoutine(scheduleRoutineArgumentCaptor.capture());
//        PersonalScheduleRoutine capturedPersonalScheduleRoutine = scheduleRoutineArgumentCaptor.getValue();
//        assertEquals(capturedPersonalScheduleRoutine.getScheduleRoutineName(), createRoutineDto.getScheduleRoutineName());
//        assertEquals(capturedPersonalScheduleRoutine.getPersonalScheduleId(), createRoutineDto.getPersonalScheduleId());
//        assertEquals(capturedPersonalScheduleRoutine.getScheduleRoutineIndex(), createRoutineDto.getScheduleRoutineIndex());
//        assertEquals(capturedPersonalScheduleRoutine.getScheduleRoutineTime(), createRoutineDto.getScheduleRoutineTime());
//    }
//
//    @Test
//    public void testDeletePersonalScheduleRoutine() throws Exception {
//        mockMvc.perform(delete("/api/routineSchedule")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("scheduleRoutineId","1"))
//                .andExpect(status().isOk());
//        verify(personalScheduleRoutineService).deleteScheduleRoutine(1L);
//    }
//
//    private String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
