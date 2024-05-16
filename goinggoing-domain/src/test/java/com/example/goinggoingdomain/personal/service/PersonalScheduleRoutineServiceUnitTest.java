package com.example.goinggoingdomain.personal.service;

import com.example.personal.model.PersonalSchedule;
import com.example.personal.implementation.PersonalReader;
import com.example.personal.implementation.PersonalRemover;
import com.example.personal.service.PersonalScheduleRoutineService;
import com.example.personal.implementation.PersonalWriter;
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
    private PersonalWriter personalWriter;
    @Mock
    PersonalRemover personalRemover;
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
        verify(personalWriter).modifyRoutineOrder(personalScheduleCaptor.capture());
        PersonalSchedule capturedPersonalSchedule = personalScheduleCaptor.getValue();
        assertNotEquals(personalSchedule.getPersonalScheduleTime().startTime(), capturedPersonalSchedule.getPersonalScheduleTime().startTime());
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
        verify(personalWriter).modifyRoutineOrder(any());
    }
    public static PersonalSchedule createPersonalSchedule() {
        PersonalSchedule.PersonalScheduleId personalScheduleId = new PersonalSchedule.PersonalScheduleId(1L);
        Integer personalDuration = 60;
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime doneTime = startTime.plusHours(1);
        PersonalSchedule.PersonalScheduleTime personalScheduleTime = new PersonalSchedule.PersonalScheduleTime(startTime, doneTime);
        PersonalSchedule.PersonalScheduleStatus personalScheduleStatus = new PersonalSchedule.PersonalScheduleStatus(false, false);
        RoutineWindow routineWindow = createRoutineWindow();
        User user = mock(User.class);
        PersonalSchedule.PersonalScheduleSend personalScheduleSend = new PersonalSchedule.PersonalScheduleSend(false, false);
        return PersonalSchedule.withId(
                personalScheduleId,
                personalDuration,
                personalScheduleTime,
                personalScheduleStatus,
                routineWindow,
                user,
                personalScheduleSend
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
