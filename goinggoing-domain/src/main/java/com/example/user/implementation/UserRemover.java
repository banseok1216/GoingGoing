package com.example.user.implementation;

import com.example.routine.model.Routine;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
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
