package com.example.user.service;
import com.example.user.User;
import com.example.user.UserChecker;
import com.example.user.UserReader;
import com.example.user.UserWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserWriter userWriter;
    private final UserChecker userChecker;
    public User loginOAuthUser(User userLogin) {
        if (userChecker.isDuplicate(userLogin)) {
            userWriter.saveUser(userLogin);
            return userLogin;
        }
        return null;
    }


    public User getUser(User.UserId id) {
        return userReader.readUser(id);
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
        boolean isDuplicate = userChecker.isDuplicate(userRegister);
        if (!isDuplicate) {
            User newUser = User.withoutId(
                    userRegister.getUserNickname(),
                    userRegister.getUserEmail(),
                    userRegister.getUserType(),
                    new User.Password(userRegister.getPassword().hashPassword())
            );
            userWriter.saveUser(newUser);
            return newUser;
        } else {
            return null;
        }
    }
}
