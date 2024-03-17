package com.example.goinggoing.group.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class GroupScheduleRequestDto {
    private Long scheduleId;
    private String scheduleName;
    private LocalDateTime scheduleDateTime;
    private String scheduleLocation;
}
