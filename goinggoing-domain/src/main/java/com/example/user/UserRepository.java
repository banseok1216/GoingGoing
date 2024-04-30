package com.example.user;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;

public interface UserRepository {
    User.UserId saveUser(User user);
    Routine.RoutineId saveUserRoutine(Routine routine, User user);

    void saveUserRoutines(RoutineWindow routineWindow);
    boolean check(User user);
    User readUserById(User.UserId userId);
    RoutineWindow readRoutineByUserId(User.UserId userId);
    Routine readUserRoutine(Routine.RoutineId routineId);
    void logout(User user);
    void removeUserRoutine(Routine routine);
}


