package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
public record PersonalRoutineRequest(
        Long userRoutineId,
        String userRoutineName,
        List<PersonalRoutineDetailRequest> routineDetail
) {
}
