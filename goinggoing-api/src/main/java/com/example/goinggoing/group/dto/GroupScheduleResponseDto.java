package com.example.goinggoing.group.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class GroupScheduleResponseDto {
    @Getter
    @Setter
    public static class CreateScheduleId {
        private Long scheduleId;
    }
    @Getter
    @Setter
    public static class GetSchedule {
        private Long scheduleId;
        private String scheduleName;
        private LocalDateTime scheduleDateTime;
        private String scheduleLocation;
        private Boolean scheduleDone;
        private Long personalScheduleId;
        private Integer duration;
        private Boolean scheduleStart;
        private LocalDateTime scheduleStartTime;
        private LocalDateTime scheduleDoneTime;
    }
}