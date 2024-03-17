package com.example.goinggoing.personal.mapstruct;

import com.example.goinggoing.personal.dto.PersonalScheduleRoutineRequestDto;
import com.example.goinggoing.personal.dto.PersonalScheduleRoutineResponseDto;
import com.example.goinggoingdomain.domain.personal.PersonalScheduleRoutine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface PersonalScheduleRoutineMapper {
    PersonalScheduleRoutineMapper INSTANCE = Mappers.getMapper(PersonalScheduleRoutineMapper.class);

    PersonalScheduleRoutine toUpdateEntity(PersonalScheduleRoutineRequestDto.updateRoutineDto updateRoutineDto);

    PersonalScheduleRoutine toCreateEntity(PersonalScheduleRoutineRequestDto.createRoutineDto createRoutineDto);
    PersonalScheduleRoutineResponseDto.CreateScheduleId toCreateResponseDto(Long scheduleRoutineId);
}
