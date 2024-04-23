package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

public class PersonalScheduleRoutineResponse {
    public record CreateScheduleId(
            Long scheduleRoutineId
    ){
    }
    public record GetScheduleRoutine(
            Long scheduleRoutineId,
            String scheduleRoutineName,
            Long scheduleRoutineTime,
            boolean scheduleRoutineDone
    ) {
    }
}

