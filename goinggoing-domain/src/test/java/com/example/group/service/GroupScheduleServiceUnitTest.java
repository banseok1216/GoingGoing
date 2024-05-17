package com.example.group.service;

import com.example.group.implementation.GroupUpdater;
import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.group.implementation.GroupReader;
import com.example.group.implementation.GroupRemover;
import com.example.group.implementation.GroupAppender;
import com.example.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GroupScheduleServiceUnitTest {
    @Mock
    private GroupReader groupReader;
    @Mock
    private GroupAppender groupAppender;
    @Mock
    private GroupUpdater groupUpdater;
    @Mock
    private GroupRemover groupRemover;
    @InjectMocks
    private GroupScheduleService groupScheduleService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testModifyGroupSchedule() {
        GroupSchedule groupSchedule = createGroupScheduleObject1();
        when(groupReader.readGroupSchedule(groupSchedule.getId())).thenReturn(groupSchedule);
        groupScheduleService.modifyGroupSchedule(createGroupScheduleObject2());
        ArgumentCaptor<GroupSchedule> captor = ArgumentCaptor.forClass(GroupSchedule.class);
        GroupSchedule modifiedGroupSchedule = captor.getValue();
        assertNotEquals(groupSchedule.getName(), modifiedGroupSchedule.getName());
        assertNotEquals(groupSchedule.getDescription(), modifiedGroupSchedule.getDescription());
        assertNotEquals(groupSchedule.getLocation(), modifiedGroupSchedule.getLocation());
        assertNotEquals(groupSchedule.getDate(), modifiedGroupSchedule.getDate());
    }

    @Test
    void testCreateGroupSchedule() {
        GroupSchedule groupSchedule = createGroupScheduleObject1();
        User user = createUser();
        when(groupAppender.saveGroupSchedule(groupSchedule, user)).thenReturn(new Group.GroupId(789L));
        Group.GroupId groupId = groupScheduleService.createGroupSchedule(groupSchedule, user);
        assertEquals(789L, groupId.value());
    }

    @Test
    void testDeleteUserSchedule() {
        GroupSchedule.GroupScheduleId groupScheduleId = new GroupSchedule.GroupScheduleId(123L);
        groupScheduleService.deleteUserSchedule(groupScheduleId);
        verify(groupRemover).removeGroupSchedule(groupScheduleId);
    }
    GroupSchedule createGroupScheduleObject1(){
        return GroupSchedule.withId(
                new GroupSchedule.GroupScheduleId(456L),
                "testScheduleName1",
                "testDescription1",
                "testLocation1",
                LocalDateTime.now());
    }
    GroupSchedule createGroupScheduleObject2(){
        return GroupSchedule.withId(
                new GroupSchedule.GroupScheduleId(456L),
                "testScheduleName2",
                "testDescription2",
                "testLocation2",
                LocalDateTime.now());
    }
    User createUser() {
        return User.withId(new User.UserId(123L), "testNickName", "testEmail", null, null, "testDeviceToken");
    }
}
