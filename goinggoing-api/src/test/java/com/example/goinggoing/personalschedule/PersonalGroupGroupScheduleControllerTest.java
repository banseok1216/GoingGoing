package com.example.goinggoing.personalschedule;

import com.example.goinggoing.personal.controller.PersonalScheduleController;
import com.example.goinggoing.personal.dto.PersonalScheduleRequestDto;
import com.example.goinggoing.personal.service.PersonalScheduleService;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonalScheduleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PersonalGroupGroupScheduleControllerTest {

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
        Long userId = 1L;
        GroupSchedule groupSchedule = createFakeSchedule();
        PersonalSchedule personalSchedule = createFakePerSonalSchedule(groupSchedule);
        when(personalScheduleService.getPersonalSchedule(userId,1L)).thenReturn(personalSchedule);
        mockMvc.perform(get("/api/personalSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("userId", userId)
                        .param("scheduleId","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value("1"))
                .andExpect(jsonPath("$.personalScheduleId").value("1"));
    }

    @Test
    void testGetMemberSchedule() throws Exception {
        GroupSchedule groupSchedule = createFakeSchedule();
        PersonalSchedule personalSchedule = createFakePerSonalSchedule(groupSchedule);
        when(personalScheduleService.getPersonalSchedule(1L,1L)).thenReturn(personalSchedule);
        mockMvc.perform(get("/api/memberSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("memberId", "1")
                        .param("scheduleId","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value("1"))
                .andExpect(jsonPath("$.personalScheduleId").value("1"));
    }

    @Test
    void testUpdatePersonalSchedule() throws Exception {
        PersonalScheduleRequestDto personalScheduleRequestDto = createFakeRequestDto();
        mockMvc.perform(put("/api/personalSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(personalScheduleRequestDto)))
                .andExpect(status().isOk());
    }
    private PersonalSchedule createFakePerSonalSchedule(GroupSchedule groupSchedule) {
        return PersonalSchedule.builder().personalScheduleId(1L)
                .scheduleDone(true)
                .scheduleDoneTime(LocalDateTime.now())
                .scheduleNotificationDone(true)
                .scheduleNotificationStart(true).
                scheduleStart(true).groupSchedule(groupSchedule).build();
    }
    private GroupSchedule createFakeSchedule() {
        return GroupSchedule.builder()
                .scheduleId(1L)
                .scheduleName("testSchedule")
                .scheduleDateTime(LocalDateTime.now())
                .scheduleLocation("testLocation")
                .build();
    }
    private PersonalScheduleRequestDto createFakeRequestDto(){
        PersonalScheduleRequestDto personalScheduleRequestDto = new PersonalScheduleRequestDto();
        personalScheduleRequestDto.setPersonalScheduleId(1L);
        personalScheduleRequestDto.setScheduleDone(false);
        personalScheduleRequestDto.setScheduleStart(true);
        personalScheduleRequestDto.setScheduleStartTime(LocalDateTime.now());
        personalScheduleRequestDto.setScheduleDoneTime(LocalDateTime.now());
        personalScheduleRequestDto.setDuration(1);
        return personalScheduleRequestDto;
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
