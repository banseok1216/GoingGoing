package com.example.user;

import com.example.personal.RoutineWindow;
import com.example.routine.Routine;

public interface UserRepository {
    void save(User user);
    User read(User user);
    boolean check(User user);
    User readUserById(User.UserId userId);
    RoutineWindow readRoutineByUserId(User.UserId userId);
    Routine readUserRoutine(Routine.RoutineId routineId);
    void removeUser(User user);
    void removeUserRoutine(Routine routine);
}


