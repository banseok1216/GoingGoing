package com.example.group.controller;

import com.example.group.service.GroupMemberService;
import com.example.personal.PersonalSchedule;
import com.example.user.User;
import com.example.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupMemberController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GroupMemberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GroupMemberService groupMemberService;
    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGroupMember() throws Exception {
        List<User> fakeUserList = new ArrayList<>();
        fakeUserList.add(User.withId(new User.UserId(123L), "testName1", null, null, null, null));
        fakeUserList.add(User.withId(new User.UserId(456L), "testName2", null, null, null, null));
        when(groupMemberService.getGroupMember(any())).thenReturn(fakeUserList);
        mockMvc.perform(get("/api/v2/group")
                        .param("groupId","123"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data[0].userId").value(123L))
                .andExpect(jsonPath("$.data[0].userNickname").value("testName1"))
                .andExpect(status().isOk());
        verify(groupMemberService, times(1)).getGroupMember(any());
    }
    @Test
    public void testInviteGroupMember() throws Exception {
        User user = User.withId(new User.UserId(123L), "testName1", null, null, null, null);
        when(userService.getUser(any())).thenReturn(user);
        mockMvc.perform(post("/api/v2/group/invite")
                        .param("groupId","123")
                        .requestAttr("userId","123"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(status().isOk());
        groupMemberService.sendInviteMessage(any(),any(User.class));
    }
    @Test
    public void testAppendGroupMember() throws Exception {
        PersonalSchedule.PersonalScheduleId personalScheduleId = new PersonalSchedule.PersonalScheduleId(123L);
        when(groupMemberService.addGroupMember(any(),any())).thenReturn(personalScheduleId);
        when(userService.getUser(any())).thenReturn(User.withId(new User.UserId(123L),null,null,null,null,null));
        mockMvc.perform(post("/api/v2/group")
                        .param("groupId","123")
                        .requestAttr("userId","123"))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.id").value(personalScheduleId.value()))
                .andExpect(status().isOk());
        verify(groupMemberService, times(1)).addGroupMember(any(),any());
    }
}
