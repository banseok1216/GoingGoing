package com.example.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;
    public User read(User user){
        return userRepository.read(user);
    }
    public User readUserById(User.UserId userId){
        return userRepository.readUserById(userId);
    }
}

