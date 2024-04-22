package com.example.group;

import com.example.user.User;

import java.util.List;

public interface GroupRepository {
    void save(Group group);
    Group read(Group.GroupId groupId);
    List<User> readGroupUser(Group.GroupId groupId);
    void remove(Group group);
}
