package com.example.personal;

import com.example.user.User;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalEntityRepository implements PersonalRepository {
    @Override
    public void save(Personal personal) {

    }

    @Override
    public Personal read(Personal personal) {
        return null;
    }

    @Override
    public Personal readByUserId(User.UserId userId) {
        return null;
    }
}
