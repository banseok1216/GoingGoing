//package com.example.goinggoing.personal.service;
//
//import com.example.personal.dto.PersonalRoutineRequestDto;
//import com.example.personal.dto.PersonalRoutineDetailRequestDto;
//import com.example.goinggoingdomain.domain.personal.ScheduleRoutine;
//import com.example.goinggoingdomain.domain.personal.PersonalUserRoutineDetail;
//import com.example.goinggoingdomain.domain.personal.repository.UserRoutineDetailRepository;
//import com.example.goinggoingdomain.domain.personal.repository.PersonalRoutineRepository;
//import com.example.goinggoingdomain.domain.user.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class PersonalRoutineServiceUnitTest {
//    @Mock
//    private PersonalRoutineRepository personalRoutineRepository;
//    @Mock
//    private UserRoutineDetailRepository personalRoutineDetailRepository;
//    @InjectMocks
//    private PersonalRoutineService personalRoutineService;
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAllUserRoutineInfo() {
//        Long userId = 1L;
//        List<ScheduleRoutine> personalRoutines = List.of(new ScheduleRoutine(), new ScheduleRoutine());
//        when(personalRoutineRepository.findAllByUserUserIdWithUserRoutineDetail(userId)).thenReturn(personalRoutines);
//        List<ScheduleRoutine> result = personalRoutineService.getAllUserRoutineInfo(userId);
//        assertEquals(personalRoutines, result);
//    }
//
//    @Test
//    void deleteUserRoutineInfo() {
//        Long userRoutineId = 1L;
//        ScheduleRoutine fakePersonalRoutine = ScheduleRoutine.builder().userRoutineId(userRoutineId).build();
//        when(personalRoutineRepository.findByUserRoutineId(userRoutineId)).thenReturn(fakePersonalRoutine);
//        personalRoutineService.deletePersonalRoutineInfo(userRoutineId);
//        verify(personalRoutineRepository, times(1)).deleteByUserRoutineId(userRoutineId);
//    }
//
//    @Test
//    void deleteUserRoutineDetailInfo() {
//        Long userRoutineDetailId = 1L;
//        ScheduleRoutine fakePersonalRoutine = ScheduleRoutine.builder().userRoutineId(1L).build();
//        PersonalUserRoutineDetail fakePersonalUserRoutineDetail = PersonalUserRoutineDetail.builder().routineDetailId(userRoutineDetailId).personalRoutine(fakePersonalRoutine).build();
//        when(personalRoutineDetailRepository.findByRoutineDetailId(userRoutineDetailId)).thenReturn(fakePersonalUserRoutineDetail);
//        personalRoutineService.deletePersonalRoutineDetailInfo(userRoutineDetailId);
//        verify(personalRoutineDetailRepository, times(1)).deleteByRoutineDetailId(userRoutineDetailId);
//    }
//
//    @Test
//    void createUserRoutineInfo() {
//        String routineName = "testRoutineName";
//        String routineDetailName = "testRoutineDetailName";
//        User user = new User();
//        ScheduleRoutine personalRoutine = ScheduleRoutine.builder().userRoutineId(1L).userRoutineName(routineName).build();
//        PersonalUserRoutineDetail personalUserRoutineDetail = PersonalUserRoutineDetail.builder().routineDetailName(routineDetailName).personalRoutine(personalRoutine).build();
//        PersonalRoutineRequestDto userRoutineDto = new PersonalRoutineRequestDto();
//        userRoutineDto.setUserRoutineName(routineName);
//        PersonalRoutineDetailRequestDto detailDto = new PersonalRoutineDetailRequestDto();
//        detailDto.setRoutineDetailName(routineDetailName);
//        userRoutineDto.setRoutineDetail(Collections.singletonList(detailDto));
//        when(personalRoutineRepository.save(any(ScheduleRoutine.class))).thenReturn(personalRoutine);
//        when(personalRoutineDetailRepository.save(any(PersonalUserRoutineDetail.class))).thenReturn(personalUserRoutineDetail);
//        personalRoutineService.createPersonalRoutineInfo(userRoutineDto,user);
//    }
//}
