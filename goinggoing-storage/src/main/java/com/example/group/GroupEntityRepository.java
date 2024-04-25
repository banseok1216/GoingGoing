package com.example.group;

import com.example.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupEntityRepository implements GroupRepository {

    @Override
    public Group.GroupId save(Group group) {

        return null;
    }

    @Override
    public void saveGroupSchedule(GroupSchedule groupSchedule, User user) {

    }

    @Override
    public void updateGroupSchedule(GroupSchedule groupSchedule) {

    }

    @Override
    public Group readGroup(Group.GroupId groupId) {
        return null;
    }

    @Override
    public List<User> readGroupUser(Group.GroupId groupId) {
        return null;
    }

    @Override
    public List<GroupSchedule> readGroupSchedules(User user) {
        return null;
    }

    @Override
    public GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId) {
        return null;
    }

    @Override
    public void removeGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId) {

    }
}
