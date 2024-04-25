package com.example.user.dto;

import com.example.routine.RoutineWindow;
import java.util.List;
import java.util.stream.Collectors;

public record UserRoutineResponse(
        Long routineId,
        Long routineTime,
        String routineName,
        Integer index
) {
    public static List<UserRoutineResponse> of(RoutineWindow routineWindow) {
        return routineWindow.getRoutines().stream()
                .map(routine -> new UserRoutineResponse(
                        routine.getRoutineId().value(),
                        routine.getRoutineTime(),
                        routine.getRoutineName(),
                        routine.getIndex()
                ))
                .collect(Collectors.toList());
    }
}
