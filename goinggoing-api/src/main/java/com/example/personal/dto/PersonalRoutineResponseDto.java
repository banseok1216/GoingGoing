package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PersonalRoutineResponseDto {
    @Getter
    @Setter
    public static class GetUserRoutine {
        private Long UserRoutineId;
        private String UserRoutineName;
        private List<PersonalRoutineDetailResponseDto> userRoutineDetails;
    }
    @Getter
    @Setter
    public static class PostUserRoutine {
        private Long UserRoutineId;
    }

}
