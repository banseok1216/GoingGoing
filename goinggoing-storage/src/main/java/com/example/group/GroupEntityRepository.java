package com.example.group;

import com.example.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupEntityRepository implements GroupRepository {

    @Override
    public void save(Group group) {

    }

    @Override
    public Group read(Group.GroupId groupId) {
        return null;
    }

    @Override
    public List<User> readGroupUser(Group.GroupId groupId) {
        return null;
    }


    @Override
    public void remove(Group group) {

    }
}
