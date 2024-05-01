package com.example.group.service;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import com.example.group.repository.GroupRepository;
import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.personal.model.PersonalSchedule;
import com.example.user.domain.User;
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
    public List<Group> readGroupList(User user){
        return groupRepository.readGroupList(user);
    }
    public GroupSchedule readGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId){
        GroupSchedule groupSchedule= groupRepository.readGroupSchedule(groupScheduleId);
        if (groupSchedule == null){
            throw new BusinessException(ErrorCode.GROUP_SCHEDULE_NOT_FOUND);
        }
        return groupRepository.readGroupSchedule(groupScheduleId);
    }
    public List<Group> readPersonalSchedulesWithNotStart() {
        return groupRepository.findNotStartedPersonalSchedules();
    }
    public List<Group> readPersonalSchedulesWithNotDone() {
        return groupRepository.findNotDonePersonalSchedules();
    }
}
