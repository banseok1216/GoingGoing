package com.example.user;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
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

