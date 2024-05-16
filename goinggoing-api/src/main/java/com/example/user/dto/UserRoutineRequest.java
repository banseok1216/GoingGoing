package com.example.user.dto;

import com.example.routine.model.Routine;

public record UserRoutineRequest(
        Long routineTime,
        String routineName,
        Integer index
) {
    public Routine toRoutine(){
        return Routine.withoutId(this.routineTime,
                this.routineName,this.index);
    }
}
