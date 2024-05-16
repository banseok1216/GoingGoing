package com.example.group.repository;

import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.personal.model.PersonalSchedule;
import com.example.user.model.User;

import java.util.List;

public interface GroupRepository {
    PersonalSchedule.PersonalScheduleId addMember(Group group, PersonalSchedule personalSchedule);

    Group.GroupId saveGroupSchedule(GroupSchedule groupSchedule, User user);
    void updateGroupSchedule(GroupSchedule groupSchedule);

    List<Group> findNotStartedPersonalSchedules();

    List<Group> findNotDonePersonalSchedules();

    Group readGroup(Group.GroupId groupId);
    List<Group> readGroupList(User user);
    GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId);

    void removeGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId);
}
