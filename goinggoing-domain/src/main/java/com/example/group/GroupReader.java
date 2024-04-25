package com.example.group;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupReader {
    private final GroupRepository groupRepository;
    public Group readGroup(Group.GroupId groupId){
        Group group = groupRepository.readGroup(groupId);
        if (group == null){
            throw new BusinessException(ErrorCode.GROUP_NOT_FOUND);
        }
        return group;
    }
    public List<GroupSchedule> readGroupSchedules(User user){
        return groupRepository.readGroupSchedules(user);
    }
    public GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId){
        GroupSchedule groupSchedule= groupRepository.readGroupSchedule(groupScheduleId);
        if (groupSchedule == null){
            throw new BusinessException(ErrorCode.GROUP_NOT_FOUND);
        }
        return groupRepository.readGroupSchedule(groupScheduleId);
    }
    public List<User> readGroupUsers(Group.GroupId groupId)
    {
        return groupRepository.readGroupUser(groupId);
    }
}
