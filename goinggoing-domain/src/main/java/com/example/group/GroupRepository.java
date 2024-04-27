package com.example.group;

import com.example.personal.PersonalSchedule;
import com.example.user.User;

import java.util.List;

public interface GroupRepository {
    PersonalSchedule.PersonalScheduleId addMember(Group group, PersonalSchedule personalSchedule);

    Group.GroupId saveGroupSchedule(GroupSchedule groupSchedule, User user);
    void updateGroupSchedule(GroupSchedule groupSchedule);
    Group readGroup(Group.GroupId groupId);
    List<Group> readGroupList(User user);
    GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId);

    void removeGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId);
}
