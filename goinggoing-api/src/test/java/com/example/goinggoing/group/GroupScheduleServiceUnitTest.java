package com.example.goinggoing.group;
import com.example.goinggoing.group.service.GroupScheduleService;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.group.repository.GroupScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GroupScheduleServiceUnitTest {

    @Mock
    private GroupScheduleRepository groupScheduleRepository;

    @InjectMocks
    private GroupScheduleService groupScheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("스케줄 생성")
    public void testCreateSchedule() {
        GroupSchedule savedGroupSchedule = createFakeSchedule();
        GroupSchedule groupSchedule = createFakeSchedule();
        when(groupScheduleRepository.save(savedGroupSchedule)).thenReturn(groupSchedule);
        groupScheduleService.createUserSchedule(groupSchedule);
        assertEquals(groupSchedule.getScheduleId(), savedGroupSchedule.getScheduleId());
    }

    @Test
    @DisplayName("스케줄 변경")
    public void testUpdateSchedule() {
        GroupSchedule savedGroupSchedule = createFakeSchedule();
        GroupSchedule groupSchedule = createFakeSchedule();
        when(groupScheduleRepository.findByScheduleId(groupSchedule.getScheduleId())).thenReturn(savedGroupSchedule);
        groupScheduleService.updateUserSchedule(groupSchedule);
        assertEquals(groupSchedule.getScheduleId(), savedGroupSchedule.getScheduleId());
    }

    @Test
    @DisplayName("스케줄 삭제")
    public void testDeleteSchedule() {
        GroupSchedule savedGroupSchedule = createFakeSchedule();
        GroupSchedule groupSchedule = createFakeSchedule();
        when(groupScheduleRepository.findByScheduleId(groupSchedule.getScheduleId())).thenReturn(savedGroupSchedule);
        groupScheduleService.updateUserSchedule(groupSchedule);
        assertEquals(groupSchedule.getScheduleId(), savedGroupSchedule.getScheduleId());
    }

    private GroupSchedule createFakeSchedule() {
        return GroupSchedule.builder()
                .scheduleId(1L)
                .scheduleName("testSchedule")
                .scheduleDateTime(LocalDateTime.now())
                .scheduleLocation("testLocation")
                .build();
    }
}
