package com.example.user.service;

import com.example.routine.domain.Routine;
import com.example.routine.domain.RoutineWindow;
import com.example.user.domain.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWriter {
    private final UserRepository userRepository;

    public User.UserId saveUser(User user)
    {
        return userRepository.saveUser(user);
    }

    public Routine.RoutineId saveUserRoutine(Routine userRoutine, User user) {
        return userRepository.saveUserRoutine(userRoutine, user);
    }

    public void saveUserRoutines(RoutineWindow userRoutines) {
        userRepository.saveUserRoutines(userRoutines);
    }
}

