package com.example.group.dto;


import java.time.LocalDateTime;

public record GroupScheduleRequest(
        Long groupId,
        Long groupScheduleId,
        String groupScheduleName,
        LocalDateTime groupScheduleDateTime,
        String groupScheduleLocation,
        String groupDescription
) {}

