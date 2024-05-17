package com.example.user.service;

import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.implementation.UserReader;
import com.example.user.implementation.UserRemover;
import com.example.user.implementation.UserAppender;
import com.example.user.model.UserRoutineWindow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRoutineService {
    private final UserReader userReader;
    private final UserRemover userRemover;
    private final UserAppender userAppender;

    public UserRoutineWindow getAllUserRoutine(User user) {
        return userReader.readUserRoutines(user.getId());
    }

    @Transactional
    public void deleteUserRoutine(User user,Routine.RoutineId userRoutineId) {
        UserRoutineWindow routineWindow = userReader.readUserRoutines(user.getId());
        routineWindow.removeRoutine(userRoutineId);
        routineWindow.updateRoutineOrder();
        userAppender.saveUserRoutines(routineWindow);
    }

    public Routine.RoutineId createUserRoutine(Routine routine, User user) {
        return userAppender.saveUserRoutine(routine,user);
    }
}
