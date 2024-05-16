package com.example.routine.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Routine {
    private final RoutineId routineId;
    private final Long routineTime;
    private final String routineName;
    private final Integer index;

    public static Routine withId(
            RoutineId routineId,
            Long routineTime,
            String routineName,
            Integer index
            ) {
        return new Routine(routineId,routineTime,routineName,index);
    }
    public static Routine withoutId(
            Long routineTime,
            String routineName,
            Integer index
    ) {
        return new Routine(null,routineTime,routineName,index);
    }
    public record RoutineId(Long value) {}
}
