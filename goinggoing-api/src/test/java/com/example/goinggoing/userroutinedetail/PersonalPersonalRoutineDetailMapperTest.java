package com.example.goinggoing.userroutinedetail;

import com.example.goinggoing.personal.dto.PersonalRoutineDetailRequestDto;
import com.example.goinggoing.personal.mapstruct.PersonalRoutineDetailMapper;
import com.example.goinggoingdomain.domain.personal.PersonalRoutine;
import com.example.goinggoingdomain.domain.personal.PersonalUserRoutineDetail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonalPersonalRoutineDetailMapperTest {

    @Test
    void testToAddEntity() {
        PersonalRoutineDetailMapper mapper = PersonalRoutineDetailMapper.INSTANCE;
        PersonalRoutineDetailRequestDto personalRoutineDetailRequestDto = new PersonalRoutineDetailRequestDto();
        personalRoutineDetailRequestDto.setRoutineDetailName("Exercise");
        personalRoutineDetailRequestDto.setRoutineDetailTime(60L);
        personalRoutineDetailRequestDto.setIndex(1);
        PersonalRoutine personalRoutine = PersonalRoutine.builder().userRoutineId(1L).build();
        PersonalUserRoutineDetail personalUserRoutineDetail = mapper.toAddEntity(personalRoutineDetailRequestDto, personalRoutine);

        assertNotNull(personalUserRoutineDetail);
        assertEquals(personalRoutineDetailRequestDto.getRoutineDetailName(), personalUserRoutineDetail.getRoutineDetailName());
        assertEquals(personalRoutineDetailRequestDto.getRoutineDetailTime(), personalUserRoutineDetail.getRoutineDetailTime());
        assertEquals(personalRoutine, personalUserRoutineDetail.getPersonalRoutine());
        assertEquals(personalRoutineDetailRequestDto.getIndex(), personalUserRoutineDetail.getIndex());
    }
}