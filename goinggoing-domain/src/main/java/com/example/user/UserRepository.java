package com.example.user;

public interface UserRepository {
    void save(User user);
    User read(User user);
    boolean check(User user);
    User readUserById(User.UserId userId);
}


