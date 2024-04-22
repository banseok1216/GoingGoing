package com.example.user;

import org.springframework.stereotype.Repository;

@Repository
public class UserEntityRepository implements UserRepository {
    @Override
    public void save(User user) {

    }
    @Override
    public User read(User user) {
        return null;
    }
    @Override
    public boolean check(User user) {
        return false;
    }

    @Override
    public User readUserById(User.UserId userId) {
        return null;
    }
}
