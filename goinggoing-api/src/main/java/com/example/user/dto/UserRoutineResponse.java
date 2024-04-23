package com.example.user.dto;

public record UserRoutineResponse(
        Long routineDetailId,
        Long routineDetailTime,
        String routineDetailName,
        Integer index
) {
}
