package com.example.goinggoing.group;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.goinggoing.group.dto.GroupMemeberDto;
import com.example.goinggoing.group.service.GroupMemberService;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.group.repository.GroupScheduleRepository;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.example.goinggoingdomain.domain.personal.repository.PersonalScheduleRepository;
import com.example.goinggoingdomain.domain.user.User;
import com.example.goinggoingdomain.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class GroupMemberServiceUnitTest {

    @Mock
    private PersonalScheduleRepository personalScheduleRepository;
    @Mock
    private GroupScheduleRepository groupScheduleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GroupMemberService groupMemberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Service - 그룹원 검색")
    void testGetGroupMember() {
        List<PersonalSchedule> personalSchedules = new ArrayList<>();
        User testUser1 = User.builder().userId(1L).userNickname("testUser1").build();
        User testUser2 = User.builder().userId(2L).userNickname("testUser2").build();
        PersonalSchedule personalSchedule1 = PersonalSchedule.builder().user(testUser1).build();
        PersonalSchedule personalSchedule2 = PersonalSchedule.builder().user(testUser2).build();
        personalSchedules.add(personalSchedule1);
        personalSchedules.add(personalSchedule2);
        GroupMemeberDto groupMemeberDto = new GroupMemeberDto();
        groupMemeberDto.setScheduleId(123L);

        when(personalScheduleRepository.findAllByScheduleScheduleId(anyLong())).thenReturn(personalSchedules);
        List<User> groupMembers = groupMemberService.getGroupMember(groupMemeberDto);

        assertEquals(2, groupMembers.size());
        assertEquals("testUser1", groupMembers.get(0).getUserNickname());
        assertEquals("testUser2", groupMembers.get(1).getUserNickname());

    }

    @Test
    @DisplayName("Service - 그룹원 추가")
    void testAddGroupMember() {
        GroupMemeberDto groupMemeberDto = new GroupMemeberDto();
        groupMemeberDto.setScheduleId(123L);
        groupMemeberDto.setUserId(456L);

        User user = User.builder().userId(123L).build();
        GroupSchedule groupSchedule =  GroupSchedule.builder().scheduleId(456L).build();
        PersonalSchedule savedpersonalSchedule = PersonalSchedule.builder().personalScheduleId(789L).build();

        when(userRepository.findByUserId(groupMemeberDto.getUserId())).thenReturn(user);
        when(groupScheduleRepository.findByScheduleId(groupMemeberDto.getScheduleId())).thenReturn(groupSchedule);
        when(personalScheduleRepository.save(any(PersonalSchedule.class))).thenReturn(savedpersonalSchedule);

        Long result = groupMemberService.addGroupMember(groupMemeberDto);
        assertNotNull(result);
        assertEquals(789L, result);
        assertEquals(789L, savedpersonalSchedule.getPersonalScheduleId());

        verify(groupScheduleRepository, times(1)).findByScheduleId(123L);
        verify(userRepository, times(1)).findByUserId(456L);
        verify(personalScheduleRepository, times(1)).save(any(PersonalSchedule.class));
    }
}
