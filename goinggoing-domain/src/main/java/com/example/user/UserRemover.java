package com.example.user;

import com.example.routine.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRemover {
    private final UserRepository userRepository;
    public void logout(User user){
        userRepository.logout(user);
    }
    public void removeUserRoutine(Routine routine){
        userRepository.removeUserRoutine(routine);
    }
}
