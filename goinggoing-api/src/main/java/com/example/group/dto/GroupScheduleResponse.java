package com.example.group.dto;

import java.time.LocalDateTime;
public record GroupScheduleResponse(
        CreateScheduleId createScheduleId,
        GetGroupSchedule getSchedule
) {
    public record CreateScheduleId(Long groupScheduleId) {}

    public record GetGroupSchedule(
            Long groupScheduleId,
            String groupScheduleName,
            LocalDateTime groupScheduleDateTime,
            String groupScheduleLocation,
            Boolean groupScheduleDone,
            Long personalScheduleId
    ) {}
}