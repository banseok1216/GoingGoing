package com.example.personal.dto;

import lombok.Getter;
import lombok.Setter;

public record PersonalRoutineDetailResponse(
        Long routineDetailId,
        Long routineDetailTime,
        String routineDetailName,
        Integer index
) {
}
