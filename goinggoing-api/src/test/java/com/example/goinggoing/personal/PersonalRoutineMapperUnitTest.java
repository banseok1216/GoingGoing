//package com.example.goinggoing.personal;
//
//import com.example.personal.dto.PersonalRoutineResponseDto;
//import com.example.goinggoing.personal.mapper.PersonalRoutineMapper;
//import com.example.goinggoingdomain.domain.personal.ScheduleRoutine;
//import com.example.goinggoingdomain.domain.controller.User;
//import org.junit.jupiter.api.Test;
//import org.mapper.factory.Mappers;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PersonalRoutineMapperUnitTest {
//
//    private final PersonalRoutineMapper mapper = Mappers.getMapper(PersonalRoutineMapper.class);
//
//    @Test
//    void testToOnlyUserRoutineEntity() {
//        String userRoutineName = "testRoutine";
//        User fakeUser = User.builder().userId(1L).build();
//        ScheduleRoutine personalRoutine = mapper.toOnlyUserRoutineEntity(userRoutineName, fakeUser);
//        assertEquals(userRoutineName, personalRoutine.getUserRoutineName());
//        assertEquals(personalRoutine.getUser().getUserId(), fakeUser.getUserId());
//    }
//
//    @Test
//    void testToAllUserRoutine() {
//        List<ScheduleRoutine> personalRoutineList = new ArrayList<>();
//        ScheduleRoutine personalRoutine1 = ScheduleRoutine.builder().userRoutineName("testRoutine1").build();
//        personalRoutineList.add(personalRoutine1);
//        ScheduleRoutine personalRoutine2 = ScheduleRoutine.builder().userRoutineName("testRoutine2").build();
//        personalRoutineList.add(personalRoutine2);
//        List<PersonalRoutineResponseDto.GetUserRoutine> userRoutineResponseDtoList = mapper.toAllUserRoutine(personalRoutineList);
//        assertEquals(2, userRoutineResponseDtoList.size());
//        assertEquals(personalRoutineList.get(0).getUserRoutineName(), userRoutineResponseDtoList.get(0).getUserRoutineName());
//        assertEquals(personalRoutineList.get(1).getUserRoutineName(), userRoutineResponseDtoList.get(1).getUserRoutineName());
//    }
//}
