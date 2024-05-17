package com.example.user.service;

import com.example.error.BusinessException;
import com.example.user.model.User;
import com.example.user.implementation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.error.ErrorCode.USER_LOGIN_PASSWORD_FAIL;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserCachedHandler userCachedHandler;
    private final UserAppender userAppender;
    private final UserChecker userChecker;
    private final UserRemover userRemover;
    private final UserUpdater userUpdater;

    public User loginOAuthUser(User userLogin) {
        if(userChecker.isNotExist(userLogin)){
            User.UserId userId = userAppender.saveUser(userLogin);
            return userReader.readUser(userId);
        };
        return userReader.readUserByEmail(userLogin);
    }

    public User getUser(User.UserId id) {
        User cachedUser = userCachedHandler.get(id.value().toString());
        if (cachedUser == null) {
            User user = userReader.readUser(id);
            userCachedHandler.put(id.value().toString(), user);
            return user;
        } else {
            return cachedUser;
        }
    }

    public User loginLocalUser(User userLogin) {
        User user = userReader.readUser(userLogin.getId());
        if (userLogin.getPassword().matches(user.getPassword().password())) {
            return user;
        } else {
            throw new BusinessException(USER_LOGIN_PASSWORD_FAIL);
        }
    }
    public void registerUser(User userRegister) {
        userChecker.isDuplicate(userRegister);
        userAppender.saveUser(userRegister.hashedPassword());
    }

    public void modifyUser(User updateUser) {
        User savedUser = getUser(updateUser.getId());
        User updatedUser = savedUser.update(updateUser);
        userUpdater.updateUser(updatedUser);
        userUpdater.refreshCachedUser(updatedUser);
    }
    public void logout(User.UserId userId) {
        User savedUser = getUser(userId);
        userRemover.logout(savedUser);
        userCachedHandler.remove(userId.toString());
        userUpdater.refreshCachedUser(savedUser);
    }
    public void refreshCachedUser(User.UserId userId){
        userCachedHandler.remove(userId.toString());
    }
}
