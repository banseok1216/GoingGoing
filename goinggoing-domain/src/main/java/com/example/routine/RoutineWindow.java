package com.example.routine;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.routine.Routine;
import lombok.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RoutineWindow {
    private final List<Routine> routines;

    public long calculateTotalTime() {
        return this.routines.stream()
                .mapToLong(Routine::getRoutineTime)
                .sum();
    }

    public RoutineWindow(@NonNull List<Routine> routines) {
        this.routines = routines;
    }
    public List<Routine> getRoutines() {
        return Collections.unmodifiableList(this.routines);
    }
    public int getNextRoutinesIndex() {
        return this.routines.size() + 1;
    }
    public void addRoutine(Routine routine) {
        this.routines.add(routine);
    }
    public void modifyRoutine(Routine userRoutine) {
        Optional<Routine> existingRoutineOptional = this.routines.stream()
                .filter(routine -> routine.getRoutineId().equals(userRoutine.getRoutineId()))
                .findFirst();
        if (existingRoutineOptional.isEmpty()) {
            throw new BusinessException(ErrorCode.USER_ROUTINE_NOT_FOUND);
        }
        Routine existingRoutine = existingRoutineOptional.get();
        Routine updatedRoutine = Routine.withId(
                existingRoutine.getRoutineId(),
                userRoutine.getRoutineTime(),
                userRoutine.getRoutineName(),
                userRoutine.getIndex()
        );
        this.routines.set(this.routines.indexOf(existingRoutine), updatedRoutine);
    }
    public void changeRoutineOrderByRemove(Routine removedRoutine) {
        this.routines.stream()
                .filter(routine -> routine.getIndex() > removedRoutine.getIndex())
                .sorted(Comparator.comparingInt(Routine::getIndex))
                .forEach(existingRoutine -> {
                    Routine updatedRoutine = Routine.withId(
                            existingRoutine.getRoutineId(),
                            existingRoutine.getRoutineTime(),
                            existingRoutine.getRoutineName(),
                            existingRoutine.getIndex() - 1);
                    this.routines.set(this.routines.indexOf(existingRoutine), updatedRoutine);
                });
    }
}
