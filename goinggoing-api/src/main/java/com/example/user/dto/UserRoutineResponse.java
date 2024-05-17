package com.example.user.dto;

import com.example.routine.model.RoutineWindow;
import com.example.user.model.UserRoutineWindow;

import java.util.List;
import java.util.stream.Collectors;

public record UserRoutineResponse(
        Long routineId,
        Long routineTime,
        String routineName,
        Integer index
) {
    public static List<UserRoutineResponse> of(UserRoutineWindow routineWindow) {
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
