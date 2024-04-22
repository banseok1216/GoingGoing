package com.example.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWriter {
    private final UserRepository userRepository;
    public void save(User user){
        userRepository.save(user);
    }
}

