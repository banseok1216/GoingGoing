package com.example.personal.service;

import com.example.personal.implementation.PersonalAppender;
import com.example.personal.implementation.PersonalUpdater;
import com.example.personal.model.PersonalSchedule;
import com.example.personal.implementation.PersonalReader;
import com.example.personal.implementation.PersonalRemover;
import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class PersonalScheduleRoutineServiceUnitTest {
    @Mock
    private PersonalReader personalReader;
    @Mock
    private PersonalAppender personalAppender;
    @Mock
    PersonalRemover personalRemover;
    @Mock
    PersonalUpdater personalUpdater;
    @Captor
    private ArgumentCaptor<PersonalSchedule> personalScheduleCaptor;

    @InjectMocks
    private PersonalScheduleRoutineService personalScheduleRoutineService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void modifyScheduleRoutine() {
        PersonalSchedule personalSchedule = createPersonalSchedule();
        when(personalReader.readPersonalSchedule(any())).thenReturn(personalSchedule);
        personalScheduleRoutineService.modifyScheduleRoutine(new PersonalSchedule.PersonalScheduleId(1L),createRoutine());
        verify(personalUpdater).modifyRoutineOrder(personalScheduleCaptor.capture());
        PersonalSchedule capturedPersonalSchedule = personalScheduleCaptor.getValue();
        assertNotEquals(personalSchedule.getScheduleTime().startTime(), capturedPersonalSchedule.getScheduleTime().startTime());
    }
    @Test
    public void testDeleteScheduleRoutine() {
        PersonalSchedule.PersonalScheduleId personalScheduleId = new PersonalSchedule.PersonalScheduleId(1L);
        Routine routine = mock(Routine.class);
        when(personalReader.readRoutine(routine.getRoutineId())).thenReturn(routine);
        when(personalReader.readPersonalSchedule(personalScheduleId)).thenReturn(createPersonalSchedule());
        personalScheduleRoutineService.deleteScheduleRoutine(personalScheduleId, routine.getRoutineId());
        verify(personalRemover).removeRoutine(routine);
        verify(personalReader).readPersonalSchedule(personalScheduleId);
        verify(personalUpdater).modifyRoutineOrder(any());
    }
    public static PersonalSchedule createPersonalSchedule() {
        PersonalSchedule.PersonalScheduleId personalScheduleId = new PersonalSchedule.PersonalScheduleId(1L);
        Integer personalDuration = 60;
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime doneTime = startTime.plusHours(1);
        PersonalSchedule.Time time = new PersonalSchedule.Time(startTime, doneTime);
        PersonalSchedule.Status status = new PersonalSchedule.Status(false, false);
        RoutineWindow routineWindow = createRoutineWindow();
        User user = mock(User.class);
        PersonalSchedule.Send send = new PersonalSchedule.Send(false, false);
        return PersonalSchedule.withId(
                personalScheduleId,
                personalDuration,
                time,
                status,
                routineWindow,
                user,
                send
        ).updateStatusAndTime();
    }
    public static RoutineWindow createRoutineWindow() {
        return new RoutineWindow(Arrays.asList(
                Routine.withId(new Routine.RoutineId(123L), 60L, "testName1", 1),
                Routine.withId(new Routine.RoutineId(456L), 60L, "testName2", 2)
        ));
    }

    public static Routine createRoutine() {
        return Routine.withId(new Routine.RoutineId(123L), 3600L, "testName1", 1);
    }

}
