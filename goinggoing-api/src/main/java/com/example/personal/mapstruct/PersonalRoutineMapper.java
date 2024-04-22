//package com.example.goinggoing.personal.mapper;
//
//import com.example.personal.dto.PersonalRoutineResponseDto;
//import com.example.goinggoingdomain.domain.personal.PersonalRoutine;
//import com.example.goinggoingdomain.domain.user.User;
//import org.mapper.Mapper;
//import org.mapper.Mapping;
//import org.mapper.factory.Mappers;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Mapper(componentModel = "spring")
//public interface PersonalRoutineMapper {
//
//    PersonalRoutineMapper INSTANCE = Mappers.getMapper(PersonalRoutineMapper.class);
//    PersonalRoutine toOnlyUserRoutineEntity(String userRoutineName, User user);
//    @Mapping(target = "userRoutineDetails", source = "personalRoutine.personalUserRoutineDetails")
//    PersonalRoutineResponseDto.GetUserRoutine toUserRoutine(PersonalRoutine personalRoutine);
//
//    default List<PersonalRoutineResponseDto.GetUserRoutine> toAllUserRoutine(List<PersonalRoutine> personalRoutineList) {
//        return personalRoutineList.stream()
//                .map(this::toUserRoutine)
//                .collect(Collectors.toList());
//    }
//}
