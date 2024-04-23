package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PersonalRoutineResponse {
    public record GetUserRoutine(
            Long UserRoutineId,
            String UserRoutineName,
            List<PersonalRoutineDetailResponse> userRoutineDetails
    ) {
    }
    public record PostUserRoutine(
            Long UserRoutineId
    ) {
    }
}
