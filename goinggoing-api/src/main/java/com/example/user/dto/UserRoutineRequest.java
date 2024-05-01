package com.example.user.dto;

import com.example.routine.domain.Routine;

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
