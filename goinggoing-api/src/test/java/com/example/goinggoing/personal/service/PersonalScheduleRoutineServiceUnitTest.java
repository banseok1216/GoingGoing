//package com.example.goinggoing.personal.service;
//import com.example.goinggoingdomain.domain.personal.PersonalScheduleRoutine;
//import com.example.goinggoingdomain.domain.personal.repository.PersonalScheduleRoutineRepository;
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
//    private PersonalScheduleRoutineRepository personalScheduleRoutineRepository;
//    @InjectMocks
//    private PersonalScheduleRoutineService personalScheduleRoutineService;
//
//    @Captor
//    ArgumentCaptor<PersonalScheduleRoutine> scheduleRoutineArgumentCaptor = ArgumentCaptor.forClass(PersonalScheduleRoutine.class);
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testUpdateScheduleRoutine() {
//        PersonalScheduleRoutine savedPersonalScheduleRoutine = createScheduleRoutine();
//        when(personalScheduleRoutineRepository.findByScheduleRoutineId(1L)).thenReturn(savedPersonalScheduleRoutine);
//        PersonalScheduleRoutine personalScheduleRoutineToUpdate = createScheduleRoutine();
//        personalScheduleRoutineService.updateScheduleRoutine(personalScheduleRoutineToUpdate);
//        verify(personalScheduleRoutineRepository, times(1)).findByScheduleRoutineId(1L);
//    }
//
//    @Test
//    void testCreateScheduleRoutine() {
//        PersonalScheduleRoutine personalScheduleRoutineToCreate = createScheduleRoutine();
//        when(personalScheduleRoutineRepository.save(personalScheduleRoutineToCreate)).thenReturn(personalScheduleRoutineToCreate);
//        Long createdScheduleRoutineId = personalScheduleRoutineService.createScheduleRoutine(personalScheduleRoutineToCreate);
//        assertEquals(personalScheduleRoutineToCreate.getScheduleRoutineId(), createdScheduleRoutineId);
//        verify(personalScheduleRoutineRepository, times(1)).save(personalScheduleRoutineToCreate);
//    }
//
//    @Test
//    void testDeleteScheduleRoutine() {
//        Long scheduleRoutineIdToDelete = 1L;
//        PersonalScheduleRoutine savedPersonalScheduleRoutine = createScheduleRoutine();
//        when(personalScheduleRoutineRepository.findByScheduleRoutineId(scheduleRoutineIdToDelete)).thenReturn(savedPersonalScheduleRoutine);
//        personalScheduleRoutineService.deleteScheduleRoutine(scheduleRoutineIdToDelete);
//        verify(personalScheduleRoutineRepository, times(1)).findByScheduleRoutineId(scheduleRoutineIdToDelete);
//        verify(personalScheduleRoutineRepository, times(1)).deleteByScheduleRoutineId(savedPersonalScheduleRoutine.getScheduleRoutineId());
//    }
//
//    private PersonalScheduleRoutine createScheduleRoutine(){
//        return PersonalScheduleRoutine.builder().scheduleRoutineId(1L)
//                .scheduleRoutineDone(true).scheduleRoutineTime(5L).scheduleRoutineIndex(1).scheduleRoutineName("testName").build();
//    }
//}
