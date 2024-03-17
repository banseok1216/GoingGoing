package com.example.goinggoing.group;

import com.example.goinggoing.group.controller.GroupScheduleController;
import com.example.goinggoing.group.dto.GroupScheduleRequestDto;
import com.example.goinggoing.group.service.GroupScheduleService;
import com.example.goinggoing.personal.service.PersonalScheduleService;
import com.example.goinggoing.user.service.UserService;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.example.goinggoingdomain.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(GroupScheduleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GroupScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupScheduleService groupScheduleService;

    @MockBean
    private UserService userService;

    @MockBean
    private PersonalScheduleService personalScheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetSchedule() throws Exception {
        Long userId = 1L;
        List<PersonalSchedule> personalScheduleList = new ArrayList<>();
        GroupSchedule groupSchedule = createFakeSchedule();
        PersonalSchedule personalSchedule = createFakePerSonalSchedule(groupSchedule);
        personalScheduleList.add(personalSchedule);
        when(personalScheduleService.getAllPersonalSchedule(userId)).thenReturn(personalScheduleList);

        ResultActions resultActions = mockMvc.perform(get("/api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("userId", 1L))
                .andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$[0].personalScheduleId").value(1L))
                .andExpect(jsonPath("$[0].scheduleId").value(1L))
                .andExpect(jsonPath("$[0].scheduleDone").exists());
    }

    @Test
    void tesCreateSchedule() throws Exception {
        Long userId = 1L;
        GroupScheduleRequestDto groupScheduleRequestDto = new GroupScheduleRequestDto();
        groupScheduleRequestDto.setScheduleId(1L);
        groupScheduleRequestDto.setScheduleName("testScheduleName");
        User user = createFakeUser();
        GroupSchedule groupSchedule = createFakeSchedule();
        when(groupScheduleService.createUserSchedule(any(GroupSchedule.class))).thenReturn(groupSchedule);
        when(userService.getUser(1L)).thenReturn(user);
        mockMvc.perform(post("/api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(groupScheduleRequestDto))
                        .requestAttr("userId", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1L));
    }

    @Test
    void tesUpdateSchedule() throws Exception {
        Long userId = 1L;
        GroupScheduleRequestDto groupScheduleRequestDto = new GroupScheduleRequestDto();
        groupScheduleRequestDto.setScheduleId(1L);
        groupScheduleRequestDto.setScheduleName("testScheduleName");
        mockMvc.perform(put("/api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(groupScheduleRequestDto))
                        .requestAttr("userId", userId))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteSchedule() throws Exception {
        mockMvc.perform(delete("/api/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                .param("scheduleId","1"))
                .andExpect(status().isOk());
    }


    private GroupSchedule createFakeSchedule() {
        return GroupSchedule.builder()
                .scheduleId(1L)
                .scheduleName("testSchedule")
                .scheduleDateTime(LocalDateTime.now())
                .scheduleLocation("testLocation")
                .build();
    }

    private PersonalSchedule createFakePerSonalSchedule(GroupSchedule groupSchedule) {
        return PersonalSchedule.builder().personalScheduleId(1L)
                .scheduleDone(true)
                .scheduleDoneTime(LocalDateTime.now())
                .scheduleNotificationDone(true)
                .scheduleNotificationStart(true).
                scheduleStart(true).groupSchedule(
                        groupSchedule).build();
    }

    private User createFakeUser() {
        return User.builder()
                .userEmail("testUserEmail")
                .userNickname("testUserNickname")
                .userRole("testUserRole")
                .userType("testUserType")
                .userId(1L)
                .password("testPassword")
                .build();
    }
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
