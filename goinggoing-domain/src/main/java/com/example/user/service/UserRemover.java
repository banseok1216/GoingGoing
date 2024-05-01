package com.example.user.service;

import com.example.routine.domain.Routine;
import com.example.user.domain.User;
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
