package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

public class PersonalScheduleRoutineRequestDto {
    @Getter
    @Setter
    public static class createRoutineDto{
        private String scheduleRoutineName;
        private Long scheduleRoutineTime;
        private Long personalScheduleId;
        private Integer scheduleRoutineIndex;
    }

    @Getter
    @Setter
    public static class updateRoutineDto{
        private Long scheduleRoutineId;
        private String scheduleRoutineName;
        private Long scheduleRoutineTime;
        private Integer scheduleRoutineIndex;
    }

}


