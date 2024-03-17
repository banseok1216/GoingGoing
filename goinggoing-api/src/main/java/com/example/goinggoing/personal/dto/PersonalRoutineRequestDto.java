package com.example.goinggoing.personal.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PersonalRoutineRequestDto {
    Long userRoutineId;
    String userRoutineName;
    List<PersonalRoutineDetailRequestDto> routineDetail;
}
