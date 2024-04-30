package com.example.group.service;

import com.example.group.Group;
import com.example.group.GroupReader;
import com.example.group.GroupSchedule;
import com.example.group.GroupWriter;
import com.example.personal.PersonalSchedule;
import com.example.redis.message.MessageDto;
import com.example.redis.pub.RedisPublisher;
import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
import com.example.user.*;
import com.example.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GroupMemberServiceTest {
    @Mock
    private GroupReader groupReader;
    @Mock
    private GroupWriter groupWriter;
    @Mock
    private RedisPublisher redisPublisher;
    @InjectMocks
    private GroupMemberService groupMemberService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("그룹 소속 유저 가져오기")
    public void testGetGroupMember_Success() {
        Group.GroupId groupId = new Group.GroupId(123L);
        when(groupReader.readGroup(any())).thenReturn(createGroup());
        List<User> users = groupMemberService.getGroupMember(groupId);
        assertEquals(users.get(0).getId().value(),123L);
    }
    @Test
    @DisplayName("그룹 소속 유저 추가하기")
    public void testAppendGroupMember_Success() {
        Group.GroupId groupId = new Group.GroupId(123L);
        User user = createUser();
        when(groupReader.readGroup(any())).thenReturn(createGroup());
        when(groupWriter.addMember(any(),any())).thenReturn(new PersonalSchedule.PersonalScheduleId(789L));
        PersonalSchedule.PersonalScheduleId personalScheduleId = groupMemberService.addGroupMember(user,groupId);
        assertEquals(personalScheduleId.value(),789L);
    }

    @Test
    @DisplayName("그룹 소속 유저 초대하기")
    public void sendInviteMessage_Success() {
        Group.GroupId groupId = new Group.GroupId(123L);
        User user = createUser();
        Group group = createGroup();

        when(groupReader.readGroup(groupId)).thenReturn(group);

        groupMemberService.sendInviteMessage(groupId, user);

        ArgumentCaptor<MessageDto> messageDtoCaptor = ArgumentCaptor.forClass(MessageDto.class);
        verify(redisPublisher, times(1)).publish(messageDtoCaptor.capture());
        MessageDto capturedMessageDto = messageDtoCaptor.getValue();
        assertEquals("testDeviceToken", capturedMessageDto.getDeviceToken());
        assertEquals(123L, capturedMessageDto.getScheduleId());
    }


    public Group createGroup() {
        return  Group.withId(
                new Group.GroupId(123L),
                createGroupSchedule(),
                List.of(PersonalSchedule.initialized(createUser(), createGroupSchedule()))
                );
    }
    User createUser() {
        return User.withId(new User.UserId(123L), "testNickName", "testEmail", null, null, "testDeviceToken");
    }
    GroupSchedule createGroupSchedule(){
        return GroupSchedule.withId(
                new GroupSchedule.GroupScheduleId(456L),
                "testScheduleName",
                "testDescription",
                "testLocation",
                LocalDateTime.now());
    }
}
