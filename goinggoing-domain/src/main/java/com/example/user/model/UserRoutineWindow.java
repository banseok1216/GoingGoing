package com.example.user.model;

import com.example.routine.model.Routine;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class UserRoutineWindow {
    private final List<Routine> routines;
    public UserRoutineWindow(@NonNull List<Routine> routines) {
        this.routines = new ArrayList<>(routines);
    }
    public List<Routine> getRoutines() {
        return Collections.unmodifiableList(this.routines);
    }
    public void removeRoutine(Routine.RoutineId routineId) {
        this.routines.removeIf(routine -> routine.getRoutineId().equals(routineId));
    }
    public void updateRoutineOrder() {
        IntStream.range(0, routines.size())
                .forEach(i -> routines.get(i).setIndex(i));
    }
}
