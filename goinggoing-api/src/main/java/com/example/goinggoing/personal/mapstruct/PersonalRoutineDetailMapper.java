package com.example.goinggoing.personal.mapstruct;

import com.example.goinggoing.personal.dto.PersonalRoutineDetailRequestDto;
import com.example.goinggoingdomain.domain.personal.PersonalRoutine;
import com.example.goinggoingdomain.domain.personal.PersonalUserRoutineDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface PersonalRoutineDetailMapper {

    PersonalRoutineDetailMapper INSTANCE = Mappers.getMapper(PersonalRoutineDetailMapper.class);

    default PersonalUserRoutineDetail toAddEntity(PersonalRoutineDetailRequestDto userRoutine, PersonalRoutine personalRoutine) {
        return PersonalUserRoutineDetail.builder()
                .routineDetailName(userRoutine.getRoutineDetailName())
                .routineDetailTime(userRoutine.getRoutineDetailTime())
                .personalRoutine(personalRoutine)
                .index(userRoutine.getIndex())
                .build();
    }
}
