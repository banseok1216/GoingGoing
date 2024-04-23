package com.example.user;


import com.example.personal.RoutineWindow;
import com.example.routine.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;
    public User readUser(User.UserId userId){
        return userRepository.readUserById(userId);
    }
    public RoutineWindow readUserRoutines(User.UserId userId){
        return userRepository.readRoutineByUserId(userId);
    }
    public Routine readUserRoutine(Routine.RoutineId routineId){
        return userRepository.readUserRoutine(routineId);
    }
}

