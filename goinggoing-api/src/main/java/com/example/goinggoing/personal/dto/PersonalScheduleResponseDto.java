package com.example.goinggoing.personal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class PersonalScheduleResponseDto {
    @Getter
    @Setter
    public static class GetPersonalSchedule {
        private Long personalScheduleId;
        private Integer duration;
        private Long scheduleId;
        private List<PersonalScheduleRoutineResponseDto.GetScheduleRoutine> scheduleRoutineDtoList;
        private Boolean scheduleDone;
        private Boolean scheduleStart;
        private LocalDateTime scheduleStartTime;
        private LocalDateTime scheduleDoneTime;
    }
}