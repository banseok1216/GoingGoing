package com.example.user.implementation;

import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.model.UserRoutineWindow;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;

    public User.UserId saveUser(User user)
    {
        return userRepository.saveUser(user);
    }

    public Routine.RoutineId saveUserRoutine(Routine userRoutine, User user) {
        return userRepository.saveUserRoutine(userRoutine, user);
    }

    public void saveUserRoutines(UserRoutineWindow userRoutines) {
        userRepository.saveUserRoutines(userRoutines);
    }
}

