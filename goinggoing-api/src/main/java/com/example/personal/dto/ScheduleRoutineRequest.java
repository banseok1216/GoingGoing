package com.example.personal.dto;

import com.example.routine.Routine;

public record ScheduleRoutineRequest(
        Create create,
        Update update
) {
    public record Create(
            String routineName,
            Long routineTime,
            Integer routineIndex
    ){
        public Routine toCreateRoutine() {
            return Routine.withoutId(this.routineTime,
                    this.routineName, this.routineIndex);
        }
    }

    public record Update(
            Long routineId,
            String routineName,
            Long routineTime,
            Integer routineIndex
    ){
        public Routine toUpdateRoutine() {
            return Routine.withId(new Routine.RoutineId(routineId), this.routineTime,
                    this.routineName, this.routineIndex);
        }
    }
}
