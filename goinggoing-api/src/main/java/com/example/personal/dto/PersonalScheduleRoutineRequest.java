package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

public class PersonalScheduleRoutineRequest {
    public record createRoutine(
            String scheduleRoutineName,
            Long scheduleRoutineTime,
            Long personalScheduleId,
            Integer scheduleRoutineIndex
    ){
    }
    public record updateRoutine(
            Long scheduleRoutineId,
            String scheduleRoutineName,
            Long scheduleRoutineTime,
            Integer scheduleRoutineIndex

    ){
    }

}


