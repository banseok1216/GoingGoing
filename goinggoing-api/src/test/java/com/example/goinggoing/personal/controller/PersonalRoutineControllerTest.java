//package com.example.goinggoing.personal.controller;
//import com.example.personal.dto.PersonalRoutineRequestDto;
//import com.example.goinggoing.personal.service.PersonalRoutineService;
//import com.example.goinggoing.user.service.UserService;
//import com.example.goinggoingdomain.domain.personal.ScheduleRoutine;
//import com.example.goinggoingdomain.domain.user.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import java.util.ArrayList;
//import java.util.List;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@WebMvcTest(com.example.user.controller.PersonalRoutineController.class)
//@AutoConfigureMockMvc(addFilters = false)
//public class PersonalRoutineControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private PersonalRoutineService personalRoutineService;
//    @MockBean
//    private UserService userService;
//
//    @Test
//    void testGetUserSchedule() throws Exception {
//        List<ScheduleRoutine> personalRoutineList = new ArrayList<ScheduleRoutine>();
//        ScheduleRoutine personalRoutine = ScheduleRoutine.builder().userRoutineId(1L).userRoutineName("testRoutineName").build();
//        personalRoutineList.add(personalRoutine);
//        when(personalRoutineService.getAllUserRoutineInfo(1L))
//                .thenReturn(personalRoutineList);
//        ResultActions resultActions =  mockMvc.perform(get("/api/userRoutine")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .requestAttr("userId", 1L))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        verify(personalRoutineService, times(1)).getAllUserRoutineInfo(1L);
//        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].userRoutineId").value(1L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userRoutineName").value("testRoutineName"));
//   }
//
//    @Test
//    void testDeleteUserRoutine() throws Exception {
//        mockMvc.perform(delete("/api/userRoutine")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("userRoutineId", "1"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        verify(personalRoutineService, times(1)).deletePersonalRoutineInfo(1L);
//    }
//    @Test
//    void testDeleteUserRoutineDetail() throws Exception {
//        mockMvc.perform(delete("/api/userRoutineDetail")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("routineDetailId", "1"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        verify(personalRoutineService, times(1)).deletePersonalRoutineDetailInfo(1L);
//    }
//    @Test
//    void testAddUserSchedule() throws Exception {
//        PersonalRoutineRequestDto userRoutineRequestDto = new PersonalRoutineRequestDto();
//        User fakeUser = User.builder()
//                .userEmail("testUserEmail")
//                .userNickname("testUserNickname")
//                .userRole("testUserRole")
//                .userType("testUserType")
//                .userId(1L)
//                .password("testPassword")
//                .build();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper.writeValueAsString(userRoutineRequestDto);
//        when(userService.getUser(1L)).thenReturn(fakeUser);
//        mockMvc.perform(post("/api/userRoutine")
//                        .contentType(MediaType.APPLICATION_JSON)
//                .requestAttr("userId", 1L)
//                .content(requestBody))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        verify(userService, times(1)).getUser(1L);
//    }
//}
