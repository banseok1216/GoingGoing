package com.example.personal.dto;

import com.example.routine.Routine;
import com.example.user.User;

public record ScheduleRoutineRequest(
        Long routineId,
        String routineName,
        Long routineTime,
        Integer routineIndex
) {
    public Routine mapToCreateRoutine(){
        return Routine.withoutId(this.routineTime,
                this.routineName,this.routineIndex);
    }
    public Routine mapToUpdateRoutine(){
        return Routine.withId(new Routine.RoutineId(routineId),this.routineTime,
                this.routineName,this.routineIndex);
    }
}
