package com.example.user.dto;

public record UserRoutineRequest(
        Long routineDetailTime,
        String routineDetailName,
        Integer index
) {
}
