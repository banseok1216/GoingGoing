package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

public record ScheduleRoutineResponse(
        Long scheduleRoutineId,
        String scheduleRoutineName,
        Long scheduleRoutineTime,
        Boolean scheduleRoutineDone
) {
}
