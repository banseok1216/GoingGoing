package com.example.user.implementation;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChecker {
    private final UserRepository userRepository;
    public void isDuplicate(User user){
        if (userRepository.check(user)){
            throw new BusinessException(ErrorCode.USER_IS_DUPLICATE);
        }
    }
    public boolean isNotExist(User user){
        return userRepository.check(user);
    }
}
