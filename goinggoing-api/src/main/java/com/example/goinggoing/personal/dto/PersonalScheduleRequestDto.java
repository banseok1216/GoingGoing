package com.example.goinggoing.personal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PersonalScheduleRequestDto {
    private Integer duration;
    private Boolean scheduleDone;
    private Long personalScheduleId;
    private Boolean scheduleStart;
    private LocalDateTime scheduleStartTime;
    private LocalDateTime scheduleDoneTime;
}
