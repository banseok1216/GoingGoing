package com.example.user;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;

public interface UserRepository {
    void saveUser(User user);
    Routine.RoutineId saveUserRoutine(Routine routine, User.UserId userId);
    void saveUserRoutines(RoutineWindow routineWindow);
    User read(User user);
    boolean check(User user);
    User readUserById(User.UserId userId);
    RoutineWindow readRoutineByUserId(User.UserId userId);
    Routine readUserRoutine(Routine.RoutineId routineId);
    void removeUser(User user);
    void removeUserRoutine(Routine routine);
}


