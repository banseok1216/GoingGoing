//package com.example.goinggoing.personal.mapper;
//
//import com.example.personal.dto.PersonalRoutineDetailRequestDto;
//
//@Mapper(componentModel = "spring")
//
//public interface PersonalRoutineDetailMapper {
//
//    PersonalRoutineDetailMapper INSTANCE = Mappers.getMapper(PersonalRoutineDetailMapper.class);
//
//    default PersonalUserRoutineDetail toAddEntity(PersonalRoutineDetailRequestDto userRoutine, PersonalRoutine personalRoutine) {
//        return PersonalUserRoutineDetail.builder()
//                .routineDetailName(userRoutine.getRoutineDetailName())
//                .routineDetailTime(userRoutine.getRoutineDetailTime())
//                .personalRoutine(personalRoutine)
//                .index(userRoutine.getIndex())
//                .build();
//    }
//}
