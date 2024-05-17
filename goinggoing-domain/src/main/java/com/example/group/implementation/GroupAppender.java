package com.example.group.implementation;

import com.example.group.repository.GroupRepository;
import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.personal.model.PersonalSchedule;
import com.example.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class GroupAppender {
    private final GroupRepository groupRepository;
    public PersonalSchedule.PersonalScheduleId addMember(Group group,User user){
        return groupRepository.addMember(group,PersonalSchedule.initialized(user,group.getGroupSchedule()));
    }
    public Group.GroupId saveGroupSchedule(GroupSchedule groupSchedule, User user){
        return groupRepository.saveGroupSchedule(groupSchedule,user);
    }
}
