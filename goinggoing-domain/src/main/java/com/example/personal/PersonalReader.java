package com.example.personal;

import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonalReader {
    private final PersonalRepository personalRepository;
    public Personal readByUserId(User.UserId userId){
        return personalRepository.readByUserId(userId);
    }
}
