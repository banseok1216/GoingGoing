package com.example.goinggoing.personal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalRoutineDetailRequestDto {
    Long routineDetailTime;
    String routineDetailName;
    Integer index;
}