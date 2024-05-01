package com.example.user.service;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.user.domain.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChecker {
    private final UserRepository userRepository;
    public void isDuplicate(User user){
        if (!userRepository.check(user)){
            throw new BusinessException(ErrorCode.USER_IS_DUPLICATE);
        }
    }
}
