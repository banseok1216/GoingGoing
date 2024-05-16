package com.example.group.controller;

import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.group.service.GroupScheduleService;
import com.example.user.model.User;
import com.example.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupScheduleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GroupScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GroupScheduleService groupScheduleService;
    @MockBean
    private UserService userService;

    @Test
    public void testCreateGroupSchedule() throws Exception {
        when(groupScheduleService.createGroupSchedule(any(),any())).thenReturn(new Group.GroupId(123L));
        when(userService.getUser(any())).thenReturn(User.withId(new User.UserId(123L),null,null,null,null,null));
        mockMvc.perform(post("/api/v2/group/schedule")
                        .requestAttr("userId",123L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"groupId\": \"123\"," +
                                "\"groupScheduleName\": \"testName\"," +
                                "\"groupScheduleDateTime\": \"2018-12-15T10:00:00\"," +
                                "\"groupScheduleLocation\": \"testLocation\"," +
                                "\"groupDescription\": \"testDescription\" }"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateGroupSchedule() throws Exception {
        ArgumentCaptor<GroupSchedule> argumentCaptor = ArgumentCaptor.forClass(GroupSchedule.class);
        mockMvc.perform(put("/api/v2/group/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"groupScheduleId\": \"456\"," +
                                "\"groupScheduleName\": \"testName\"," +
                                "\"groupScheduleDateTime\": \"2018-12-15T10:00:00\"," +
                                "\"groupScheduleLocation\": \"testLocation\"," +
                                "\"groupDescription\": \"testDescription\" }"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(status().isOk());
        verify(groupScheduleService).modifyGroupSchedule(argumentCaptor.capture());
        GroupSchedule capturedGroupSchedule = argumentCaptor.getValue();
        assertEquals(456L, capturedGroupSchedule.getId().value());
        assertEquals("testName", capturedGroupSchedule.getName());
        assertEquals("testLocation", capturedGroupSchedule.getLocation());
        assertEquals("testDescription", capturedGroupSchedule.getDescription());
    }
    @Test
    public void testDeleteGroupSchedule() throws Exception {
        mockMvc.perform(delete("/api/v2/group/schedule")
                        .param("groupScheduleId","123"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(status().isOk());
        verify(groupScheduleService).deleteUserSchedule(new GroupSchedule.GroupScheduleId(123L));
    }
}
