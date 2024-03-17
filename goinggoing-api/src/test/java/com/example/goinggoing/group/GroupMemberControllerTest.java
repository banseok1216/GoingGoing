package com.example.goinggoing.group;

import com.example.goinggoing.group.controller.GroupMemberController;
import com.example.goinggoing.group.dto.GroupMemberResponseDto;
import com.example.goinggoing.group.dto.GroupMemeberDto;
import com.example.goinggoing.group.mapstruct.GroupMemberMapper;
import com.example.goinggoing.group.service.GroupMemberService;
import com.example.goinggoing.user.service.UserService;
import com.example.goinggoingdomain.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(GroupMemberController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GroupMemberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GroupMemberService groupMemberService;
    @MockBean
    private UserService userService;

    @Test
    @DisplayName("controller - 그룹원 확인 테스트")
    void testGetGroupMember() throws Exception {
        Long userId = 123L;
        Long scheduleId = 456L;
        List<User> userList = new ArrayList<>();
        User user1 = User.builder().userId(1L).userNickname("testName").build();
        User user2 = User.builder().userId(2L).userNickname("testName").build();
        userList.add(user1);
        userList.add(user2);
        when(groupMemberService.getGroupMember(any(GroupMemeberDto.class))).thenReturn(userList);
        mockMvc.perform(get("/api/group")
                        .param("scheduleId", scheduleId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("userId", userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
        GroupMemeberDto groupMemeberDto = GroupMemberMapper.INSTANCE.toGroupDto(userId, scheduleId);
        assertEquals(userId, groupMemeberDto.getUserId());
        assertEquals(scheduleId, groupMemeberDto.getScheduleId());
        List<GroupMemberResponseDto.GetGroup> getGroupList = GroupMemberMapper.INSTANCE.toGetGroupResponseDto(userList);
        assertEquals(1L, getGroupList.getFirst().getUserId());
        assertEquals("testName", getGroupList.getFirst().getUserNickname());
    }

    @Test
    @DisplayName("controller - 그룹원 추가 테스트")
    void testPostGroupMember() throws Exception {
        Long userId = 123L;
        Long scheduleId = 456L;
        Long personalScheduleId = 1L;
        ResultActions resultActions = mockMvc.perform(post("/api/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("userId", 1L)
                        .param("scheduleId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        GroupMemeberDto groupMemeberDto = GroupMemberMapper.INSTANCE.toGroupDto(userId,scheduleId);
        assertEquals(userId, groupMemeberDto.getUserId());
        assertEquals(scheduleId, groupMemeberDto.getScheduleId());
        when(groupMemberService.addGroupMember(any(GroupMemeberDto.class))).thenReturn(personalScheduleId);
        GroupMemberResponseDto.AddGroup addGroup = GroupMemberMapper.INSTANCE.toAddGroupResponseDto(personalScheduleId);
        assertEquals(personalScheduleId, addGroup.getPersonalScheduleId());
        resultActions.andExpect(jsonPath("$.personalScheduleId").exists());
    }
}
