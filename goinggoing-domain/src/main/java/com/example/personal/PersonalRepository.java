package com.example.personal;

import com.example.user.User;

public interface PersonalRepository {
    void save(Personal personal);
    Personal read(Personal personal);
    Personal readByUserId(User.UserId userId);
}
