//package com.example.goinggoingdomain.personal.service;
//
//import com.example.personal.service.PersonalReader;
//import com.example.personal.service.PersonalRemover;
//import com.example.personal.service.PersonalWriter;
//import com.example.personal.service.PersonalScheduleRoutineService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class PersonalScheduleRoutineServiceUnitTest {
//
//    @Mock
//    private PersonalRemover personalRemover;
//    @Mock
//    private PersonalWriter personalWriter;
//    @Mock
//    private PersonalReader personalReader;
//    @InjectMocks
//    private PersonalScheduleRoutineService personalScheduleRoutineService;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//    @Test
//    void testUpdateScheduleRoutine() {
//        ScheduleRoutine savedPersonalScheduleRoutine = createScheduleRoutine();
//        when(personalScheduleRoutineRepository.findByScheduleRoutineId(1L)).thenReturn(savedPersonalScheduleRoutine);
//        ScheduleRoutine personalScheduleRoutineToUpdate = createScheduleRoutine();
//        personalScheduleRoutineService.updateScheduleRoutine(personalScheduleRoutineToUpdate);
//        verify(personalScheduleRoutineRepository, times(1)).findByScheduleRoutineId(1L);
//    }
//
//    @Test
//    void testCreateScheduleRoutine() {
//        ScheduleRoutine personalScheduleRoutineToCreate = createScheduleRoutine();
//        when(personalScheduleRoutineRepository.save(personalScheduleRoutineToCreate)).thenReturn(personalScheduleRoutineToCreate);
//        Long createdScheduleRoutineId = personalScheduleRoutineService.createScheduleRoutine(personalScheduleRoutineToCreate);
//        assertEquals(personalScheduleRoutineToCreate.getScheduleRoutineId(), createdScheduleRoutineId);
//        verify(personalScheduleRoutineRepository, times(1)).save(personalScheduleRoutineToCreate);
//    }
//
//    @Test
//    void testDeleteScheduleRoutine() {
//        Long scheduleRoutineIdToDelete = 1L;
//        ScheduleRoutine savedPersonalScheduleRoutine = createScheduleRoutine();
//        when(personalScheduleRoutineRepository.findByScheduleRoutineId(scheduleRoutineIdToDelete)).thenReturn(savedPersonalScheduleRoutine);
//        personalScheduleRoutineService.deleteScheduleRoutine(scheduleRoutineIdToDelete);
//        verify(personalScheduleRoutineRepository, times(1)).findByScheduleRoutineId(scheduleRoutineIdToDelete);
//        verify(personalScheduleRoutineRepository, times(1)).deleteByScheduleRoutineId(savedPersonalScheduleRoutine.getScheduleRoutineId());
//    }
//
//    private ScheduleRoutine createScheduleRoutine(){
//        return ScheduleRoutine.builder().scheduleRoutineId(1L)
//                .scheduleRoutineDone(true).scheduleRoutineTime(5L).scheduleRoutineIndex(1).scheduleRoutineName("testName").build();
//    }
//}