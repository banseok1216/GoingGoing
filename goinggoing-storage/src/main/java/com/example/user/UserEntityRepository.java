package com.example.user;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
import org.springframework.stereotype.Repository;

@Repository
public class UserEntityRepository implements UserRepository {
    @Override
    public void saveUser(User user) {

    }
    @Override
    public User read(User user) {
        return null;
    }
    @Override
    public boolean check(User user) {
        return false;
    }

    @Override
    public User readUserById(User.UserId userId) {
        return null;
    }

    @Override
    public RoutineWindow readRoutineByUserId(User.UserId userId) {
        return null;
    }

    @Override
    public Routine readUserRoutine(Routine.RoutineId routineId) {
        return null;
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void removeUserRoutine(Routine routine) {

    }
}
