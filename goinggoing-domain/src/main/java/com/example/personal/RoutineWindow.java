package com.example.personal;

import com.example.routine.Routine;
import lombok.*;

import java.util.Collections;
import java.util.List;

public class RoutineWindow {
    private final List<Routine> routines;

    public long calculateTotalTime() {
        return  routines.stream()
                .mapToLong(Routine::getRoutineTime)
                .sum();
    }

    public RoutineWindow(@NonNull List<Routine> routines) {
        this.routines = routines;
    }
    public List<Routine> getRoutines() {
        return Collections.unmodifiableList(this.routines);
    }
    public void addRoutine(Routine routine) {
        this.routines.add(routine);
    }
    public void modifyScheduleRoutineDetail(Routine scheduleRoutineDetail) {
        routines.stream()
                .filter(routine -> routine.getRoutineId().equals(scheduleRoutineDetail.getRoutineId()))
                .findFirst()
                .ifPresent(existingRoutine -> {
                    Routine updatedRoutine = Routine.withId(
                            existingRoutine.getRoutineId(),
                            scheduleRoutineDetail.getRoutineTime(),
                            scheduleRoutineDetail.getRoutineName(),
                            scheduleRoutineDetail.getIndex()
                    );
                    routines.set(routines.indexOf(existingRoutine), updatedRoutine);
                });
    }
}
