package com.example.routine.model;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.routine.model.Routine;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class RoutineWindow {
    private final List<Routine> routines;
    public long calculateTotalTime() {
        return this.routines.stream()
                .mapToLong(Routine::getRoutineTime)
                .sum();
    }
    public RoutineWindow(@NonNull List<Routine> routines) {
        this.routines = new ArrayList<>(routines);
    }
    public List<Routine> getRoutines() {
        return Collections.unmodifiableList(this.routines);
    }
    public void addRoutine(Routine routine) {
        this.routines.add(routine);
    }
    public void modifyRoutine(Routine modifiedRoutine) {
        routines.set(modifiedRoutine.getIndex(), modifiedRoutine);
    }
    public void removeRoutine(Routine.RoutineId routineId) {
        this.routines.removeIf(routine -> routine.getRoutineId().equals(routineId));
    }
    public void updateRoutineOrder() {
        IntStream.range(0, routines.size())
                .forEach(i -> routines.get(i).setIndex(i));
    }
}
