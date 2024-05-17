package com.example.user.repository;

import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.model.UserRoutineWindow;

public interface UserRepository {
    User.UserId saveUser(User user);
    Routine.RoutineId saveUserRoutine(Routine routine, User user);
    void saveUserRoutines(UserRoutineWindow routineWindow);
    boolean check(User user);
    User readUserById(User.UserId userId);
    User readUserByEmail(User user);
    UserRoutineWindow readRoutineByUserId(User.UserId userId);
    Routine readUserRoutine(Routine.RoutineId routineId);
    void logout(User user);
    void removeUserRoutine(Routine routine);
}


