package com.example.user;

import com.example.personal.RoutineWindow;
import com.example.routine.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRemover {
    private final UserRepository userRepository;
    public void removeUser(User user){
        userRepository.removeUser(user);
    }
    public void removeUserRoutine(Routine routine){
        userRepository.removeUserRoutine(routine);
    }
}
