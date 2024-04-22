//package com.example.goinggoing.personal.mapper;
//
//import com.example.personal.dto.PersonalScheduleRoutineRequestDto;
//import com.example.personal.dto.PersonalScheduleRoutineResponseDto;
//import com.example.goinggoingdomain.domain.personal.PersonalScheduleRoutine;
//import org.mapper.Mapper;
//import org.mapper.factory.Mappers;
//
//@Mapper(componentModel = "spring")
//
//public interface PersonalScheduleRoutineMapper {
//    PersonalScheduleRoutineMapper INSTANCE = Mappers.getMapper(PersonalScheduleRoutineMapper.class);
//
//    PersonalScheduleRoutine toUpdateEntity(PersonalScheduleRoutineRequestDto.updateRoutineDto updateRoutineDto);
//
//    PersonalScheduleRoutine toCreateEntity(PersonalScheduleRoutineRequestDto.createRoutineDto createRoutineDto);
//    PersonalScheduleRoutineResponseDto.CreateScheduleId toCreateResponseDto(Long scheduleRoutineId);
//}
