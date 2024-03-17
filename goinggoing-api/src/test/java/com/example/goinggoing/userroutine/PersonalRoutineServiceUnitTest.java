package com.example.goinggoing.userroutine;

import com.example.goinggoing.personal.dto.PersonalRoutineRequestDto;
import com.example.goinggoing.personal.dto.PersonalRoutineDetailRequestDto;
import com.example.goinggoing.personal.service.PersonalRoutineService;
import com.example.goinggoingdomain.domain.personal.PersonalRoutine;
import com.example.goinggoingdomain.domain.personal.PersonalUserRoutineDetail;
import com.example.goinggoingdomain.domain.personal.repository.PersonalRoutineDetailRepository;
import com.example.goinggoingdomain.domain.personal.repository.PersonalRoutineRepository;
import com.example.goinggoingdomain.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonalRoutineServiceUnitTest {
    @Mock
    private PersonalRoutineRepository personalRoutineRepository;
    @Mock
    private PersonalRoutineDetailRepository personalRoutineDetailRepository;
    @InjectMocks
    private PersonalRoutineService personalRoutineService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUserRoutineInfo() {
        Long userId = 1L;
        List<PersonalRoutine> personalRoutines = List.of(new PersonalRoutine(), new PersonalRoutine());
        when(personalRoutineRepository.findAllByUserUserIdWithUserRoutineDetail(userId)).thenReturn(personalRoutines);
        List<PersonalRoutine> result = personalRoutineService.getAllUserRoutineInfo(userId);
        assertEquals(personalRoutines, result);
    }

    @Test
    void deleteUserRoutineInfo() {
        Long userRoutineId = 1L;
        PersonalRoutine fakePersonalRoutine = PersonalRoutine.builder().userRoutineId(userRoutineId).build();
        when(personalRoutineRepository.findByUserRoutineId(userRoutineId)).thenReturn(fakePersonalRoutine);
        personalRoutineService.deletePersonalRoutineInfo(userRoutineId);
        verify(personalRoutineRepository, times(1)).deleteByUserRoutineId(userRoutineId);
    }

    @Test
    void deleteUserRoutineDetailInfo() {
        Long userRoutineDetailId = 1L;
        PersonalRoutine fakePersonalRoutine = PersonalRoutine.builder().userRoutineId(1L).build();
        PersonalUserRoutineDetail fakePersonalUserRoutineDetail = PersonalUserRoutineDetail.builder().routineDetailId(userRoutineDetailId).personalRoutine(fakePersonalRoutine).build();
        when(personalRoutineDetailRepository.findByRoutineDetailId(userRoutineDetailId)).thenReturn(fakePersonalUserRoutineDetail);
        personalRoutineService.deletePersonalRoutineDetailInfo(userRoutineDetailId);
        verify(personalRoutineDetailRepository, times(1)).deleteByRoutineDetailId(userRoutineDetailId);
    }

    @Test
    void createUserRoutineInfo() {
        String routineName = "testRoutineName";
        String routineDetailName = "testRoutineDetailName";
        User user = new User();
        PersonalRoutine personalRoutine = PersonalRoutine.builder().userRoutineId(1L).userRoutineName(routineName).build();
        PersonalUserRoutineDetail personalUserRoutineDetail = PersonalUserRoutineDetail.builder().routineDetailName(routineDetailName).personalRoutine(personalRoutine).build();
        PersonalRoutineRequestDto userRoutineDto = new PersonalRoutineRequestDto();
        userRoutineDto.setUserRoutineName(routineName);
        PersonalRoutineDetailRequestDto detailDto = new PersonalRoutineDetailRequestDto();
        detailDto.setRoutineDetailName(routineDetailName);
        userRoutineDto.setRoutineDetail(Collections.singletonList(detailDto));
        when(personalRoutineRepository.save(any(PersonalRoutine.class))).thenReturn(personalRoutine);
        when(personalRoutineDetailRepository.save(any(PersonalUserRoutineDetail.class))).thenReturn(personalUserRoutineDetail);
        personalRoutineService.createPersonalRoutineInfo(userRoutineDto,user);
    }
}
