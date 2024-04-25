package com.example.group;

import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupReader {
    private final GroupRepository groupRepository;
    public Group read(Group.GroupId groupId){
        return groupRepository.read(groupId);
    }
    public List<GroupSchedule> readGroupSchedules(User.UserId userId){
        return groupRepository.readGroupSchedules(userId);
    }
    public GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId){
        return groupRepository.readGroupSchedule(groupScheduleId);
    }
    public List<User> readGroupUsers(Group.GroupId groupId){
        return groupRepository.readGroupUser(groupId);
    }
}
