package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

public record PersonalRoutineDetailRequest(
        Long routineDetailTime,
        String routineDetailName,
        Integer index
) {
}
