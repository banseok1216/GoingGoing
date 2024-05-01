package com.example.user.service;

import com.example.routine.domain.Routine;
import com.example.routine.domain.RoutineWindow;
import com.example.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRoutineService {
    private final UserReader userReader;
    private final UserRemover userRemover;
    private final UserWriter userWriter;

    public RoutineWindow getAllUserRoutine(User user) {
        return userReader.readUserRoutines(user.getId());
    }

    @Transactional
    public void deleteUserRoutine(User user,Routine.RoutineId userRoutineId) {
        RoutineWindow routineWindow = userReader.readUserRoutines(user.getId());
        routineWindow.changeRoutineRemove(userRoutineId);
        userWriter.saveUserRoutines(routineWindow);
    }

    public Routine.RoutineId createUserRoutine(Routine routine, User user) {
        return userWriter.saveUserRoutine(routine,user);
    }
}
