package com.example.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChecker {
    private final UserRepository userRepository;
    public boolean isDuplicate(User user){
        return userRepository.check(user);
    }
}
