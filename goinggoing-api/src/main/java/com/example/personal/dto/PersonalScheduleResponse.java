package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public record PersonalScheduleResponse(
        Long personalScheduleId,
        Integer duration,
        Long scheduleId,
        List<PersonalScheduleRoutineResponse.GetScheduleRoutine> scheduleRoutineDtoList,
        Boolean scheduleDone,
        Boolean scheduleStart,
        LocalDateTime scheduleStartTime,
        LocalDateTime scheduleDoneTime
) {
}