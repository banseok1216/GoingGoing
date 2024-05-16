package com.example.personal.dto;

import com.example.routine.model.RoutineWindow;

import java.util.List;
import java.util.stream.Collectors;

public record ScheduleRoutineResponse(
        Long scheduleRoutineId,
        String scheduleRoutineName,
        Long scheduleRoutineTime,
        Integer index
) {
    public static List<ScheduleRoutineResponse> of(RoutineWindow routineWindow) {
        return routineWindow.getRoutines().stream()
                .map(scheduleRoutine -> new ScheduleRoutineResponse(
                        scheduleRoutine.getRoutineId().value(),
                        scheduleRoutine.getRoutineName(),
                        scheduleRoutine.getRoutineTime(),
                        scheduleRoutine.getIndex()
                ))
                .collect(Collectors.toList());
    }
}
