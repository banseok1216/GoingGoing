package com.example.user.service;

import com.example.error.BusinessException;
import com.example.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.error.ErrorCode.USER_LOGIN_PASSWORD_FAIL;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserCachedReader userCachedReader;
    private final UserWriter userWriter;
    private final UserChecker userChecker;
    private final UserRemover userRemover;

    public User loginOAuthUser(User userLogin) {
        userChecker.isDuplicate(userLogin);
        User.UserId userId = userWriter.saveUser(userLogin);
        return userReader.readUser(userId);
    }

    public User getUser(User.UserId id) {
        User cachedUser = userCachedReader.get(id.value().toString());
        if (cachedUser == null) {
            User user = userReader.readUser(id);
            userCachedReader.put(id.value().toString(), user);
            return user;
        } else {
            return cachedUser;
        }
    }

    public User loginDefaultUser(User userLogin) {
        User user = userReader.readUser(userLogin.getId());
        if (userLogin.getPassword().matches(user.getPassword().password())) {
            return user;
        } else {
            throw new BusinessException(USER_LOGIN_PASSWORD_FAIL);
        }
    }
    public void registerUser(User userRegister) {
        userChecker.isDuplicate(userRegister);
        User newUser = User.withoutId(
                userRegister.getUserNickname(),
                userRegister.getUserEmail(),
                userRegister.getUserType(),
                userRegister.getPassword().hashPassword()
                , null
        );
        userWriter.saveUser(newUser);
    }

    public void modifyUser(User updateUser) {
        User savedUser = getUser(updateUser.getId());
        User newUser = User.withId(savedUser.getId(), updateUser.getUserNickname(), updateUser.getUserEmail(), updateUser.getUserType(), updateUser.getPassword(), updateUser.getDeviceToken());
        userWriter.saveUser(newUser);
        userCachedReader.put(updateUser.getId().value().toString(), newUser);
    }
    public void logout(User.UserId userId) {
        User savedUser = getUser(userId);
        userRemover.logout(savedUser);
        userCachedReader.remove(userId.toString());
    }
}
