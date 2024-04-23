package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record PersonalScheduleRequest(
        Integer personalDuration,
        Boolean personalScheduleDone,
        Long personalScheduleId,
        Boolean personalScheduleStart,
        LocalDateTime personalScheduleStartTime,
        LocalDateTime personalScheduleDoneTime
) {
}
