package com.example.user.service;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
import com.example.user.User;
import com.example.user.UserReader;
import com.example.user.UserRemover;
import com.example.user.UserWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRoutineService {
    private final UserReader userReader;
    private final UserRemover userRemover;
    private final UserWriter userWriter;

    public RoutineWindow getAllUserRoutine(User.UserId userId) {
        return userReader.readUserRoutines(userId);
    }

    @Transactional
    public void deleteUserRoutine(User.UserId userId,Routine.RoutineId userRoutineId) {
        RoutineWindow routineWindow = userReader.readUserRoutines(userId);
        Routine userRoutine = userReader.readUserRoutine(userRoutineId);
        userRemover.removeUserRoutine(userRoutine);
        routineWindow.changeRoutineOrderByRemove(userRoutine);
        userWriter.saveUserRoutines(routineWindow);
    }

    public Routine.RoutineId createUserRoutine(Routine routine, User.UserId userId) {
        return userWriter.saveUserRoutine(routine,userId);
    }
}
