package com.example.group;

import com.example.user.User;

import java.util.List;

public interface GroupRepository {
    void save(Group group);
    void saveGroupSchedule(GroupSchedule groupSchedule, User.UserId userId);
    void updateGroupSchedule(GroupSchedule groupSchedule);
    Group read(Group.GroupId groupId);
    List<User> readGroupUser(Group.GroupId groupId);
    List<GroupSchedule> readGroupSchedules(User.UserId userId);
    GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId);

    void removeGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId);
}
