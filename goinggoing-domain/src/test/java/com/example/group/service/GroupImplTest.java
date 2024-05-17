package com.example.group.service;

import com.example.error.BusinessException;
import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.group.repository.GroupRepository;
import com.example.group.implementation.GroupReader;
import com.example.group.implementation.GroupWriter;
import com.example.personal.model.PersonalSchedule;
import com.example.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GroupImplTest {
    @Mock
    private GroupRepository groupRepository;
    @InjectMocks
    private GroupReader groupReader;
    @InjectMocks
    private GroupWriter groupWriter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadGroup_GroupExists() {
        Group.GroupId groupId = new Group.GroupId(123L);
        Group group = createGroup();
        when(groupRepository.readGroup(groupId)).thenReturn(group);

        Group retrievedGroup = groupReader.readGroup(groupId);

        assertNotNull(retrievedGroup);
        assertEquals(group, retrievedGroup);
    }

    @Test
    public void testReadGroup_Exception() {
        Group.GroupId groupId = new Group.GroupId(123L);

        when(groupRepository.readGroup(groupId)).thenReturn(null);

        assertThrows(BusinessException.class, () -> {
            groupReader.readGroup(groupId);
        });
    }

    @Test
    public void testReadGroupSchedule_GroupScheduleExists() {
        GroupSchedule.GroupScheduleId groupScheduleId = new GroupSchedule.GroupScheduleId(123L);
        GroupSchedule groupSchedule = createGroupSchedule();
        when(groupRepository.readGroupSchedule(groupScheduleId)).thenReturn(groupSchedule);

        GroupSchedule retrievedGroupSchedule = groupReader.readGroupSchedule(groupScheduleId);

        assertNotNull(retrievedGroupSchedule);
        assertEquals(groupSchedule, retrievedGroupSchedule);
    }

    @Test
    public void testReadGroupSchedule_Exception() {
        GroupSchedule.GroupScheduleId groupScheduleId = new GroupSchedule.GroupScheduleId(123L);

        when(groupRepository.readGroupSchedule(groupScheduleId)).thenReturn(null);

        assertThrows(BusinessException.class, () -> {
            groupReader.readGroupSchedule(groupScheduleId);
        });
    }

    @Test
    public void testUpdateGroupSchedule() {
        GroupSchedule groupSchedule = createGroupSchedule();
        groupWriter.updateGroupSchedule(groupSchedule);
        verify(groupRepository, times(1)).updateGroupSchedule(groupSchedule);
    }

    @Test
    public void testSaveGroupSchedule() {
        GroupSchedule groupSchedule =createGroupSchedule();
        User user = createUser();

        when(groupRepository.saveGroupSchedule(groupSchedule, user)).thenReturn(new Group.GroupId(123L));

        Group.GroupId savedGroupId = groupWriter.saveGroupSchedule(groupSchedule, user);

        assertNotNull(savedGroupId);
        assertEquals(123L, savedGroupId.value());
    }

    public User createUser() {
        return User.withId(new User.UserId(123L), "user1", null, null, null, null);
    }

    public Group createGroup() {
        return Group.withId(
                new Group.GroupId(123L),
                createGroupSchedule(),
                List.of(PersonalSchedule.initialized(createUser(), createGroupSchedule()))
        );
    }

    GroupSchedule createGroupSchedule() {
        return GroupSchedule.withId(
                new GroupSchedule.GroupScheduleId(456L),
                "testScheduleName",
                "testDescription",
                "testLocation",
                LocalDateTime.now());
    }
}
