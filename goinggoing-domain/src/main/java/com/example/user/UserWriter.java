package com.example.user;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWriter {
    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    public Routine.RoutineId saveUserRoutine(Routine userRoutine, User.UserId userId) {
        return userRepository.saveUserRoutine(userRoutine, userId);
    }

    public void saveUserRoutines(RoutineWindow userRoutines) {
        userRepository.saveUserRoutines(userRoutines);
    }
}

