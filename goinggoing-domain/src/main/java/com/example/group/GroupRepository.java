package com.example.group;

import com.example.user.User;

import java.util.List;

public interface GroupRepository {
    Group.GroupId save(Group group);
    void saveGroupSchedule(GroupSchedule groupSchedule, User user);
    void updateGroupSchedule(GroupSchedule groupSchedule);
    Group readGroup(Group.GroupId groupId);
    List<User> readGroupUser(Group.GroupId groupId);
    List<GroupSchedule> readGroupSchedules(User user);
    GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId);

    void removeGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId);
}
