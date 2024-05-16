package com.example.user.implementation;

import com.example.user.external.ExternalUserSender;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;
    private final ExternalUserSender externalUserSender;
    public void updateUser(User user)
    {
        userRepository.saveUser(user);
    }
    public void refreshCachedUser(User user) {
        externalUserSender.sendUserCachedRefresh(user);
    }

}
