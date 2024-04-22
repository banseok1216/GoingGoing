package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

public class PersonalScheduleRoutineResponseDto {
    @Getter
    @Setter
    public static class CreateScheduleId {
        private Long scheduleRoutineId;
    }
    @Getter
    @Setter
    public static class GetScheduleRoutine {
        private Long scheduleRoutineId;
        private String scheduleRoutineName;
        private Long scheduleRoutineTime;
        private Integer scheduleRoutineIndex;
        private boolean scheduleRoutineDone;
    }
}

