package com.example.user.service;

import com.example.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        userWriter.saveUser(userLogin);
        return userLogin;
    }

    public User getUser(User.UserId id) {
        User cachedUser = userCachedReader.get(id.getValue().toString());
        if (cachedUser != null) {
            return cachedUser;
        } else {
            User user = userReader.readUser(id);
            if (user != null) {
                userCachedReader.put(id.getValue().toString(), user);
            }
            return user;
        }
    }

    public User loginDefaultUser(User userLogin) {
        User user = userReader.readUser(userLogin.getId());
        if (user != null && userLogin.getPassword().matches(user.getPassword().password())) {
            return user;
        } else {
            return null;
        }
    }

    public User registUser(User userRegister) {
        userChecker.isDuplicate(userRegister);
        User newUser = User.withoutId(
                userRegister.getUserNickname(),
                userRegister.getUserEmail(),
                userRegister.getUserType(),
                new User.Password(userRegister.getPassword().hashPassword())
                , null
        );
        userWriter.saveUser(newUser);
        return newUser;
    }

    public void modifyUser(User updateUser) {
        User savedUser = getUser(updateUser.getId());
        User newUser = User.withId(savedUser.getId(), updateUser.getUserNickname(), updateUser.getUserEmail(), updateUser.getUserType(), updateUser.getPassword(), updateUser.getDeviceToken());
        userWriter.saveUser(newUser);
        userCachedReader.put(updateUser.getId().getValue().toString(), newUser);
    }

    public void deleteUser(User user) {
        User savedUser = getUser(user.getId());
        userRemover.removeUser(savedUser);
        userCachedReader.remove(user.getId().getValue().toString());
    }
}
