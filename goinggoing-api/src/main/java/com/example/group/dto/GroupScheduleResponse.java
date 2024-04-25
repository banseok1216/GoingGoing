package com.example.group.dto;

import com.example.group.GroupSchedule;
import com.example.routine.RoutineWindow;
import com.example.user.dto.UserRoutineResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record GroupScheduleResponse(
            Long groupScheduleId,
            String groupScheduleName,
            LocalDateTime groupScheduleDateTime,
            String groupScheduleLocation,
            String groupScheduleDescription
    ) {
    public static List<GroupScheduleResponse> of(List<GroupSchedule> groupScheduleResponses) {
        return groupScheduleResponses.stream()
                .map(groupSchedule -> new GroupScheduleResponse(
                        groupSchedule.getId().value(),
                        groupSchedule.getName(),
                        groupSchedule.getDate(),
                        groupSchedule.getLocation(),
                        groupSchedule.getDescription()
                ))
                .collect(Collectors.toList());
    }
}