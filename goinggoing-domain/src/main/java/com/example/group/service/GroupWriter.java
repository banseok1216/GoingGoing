package com.example.group.service;

import com.example.group.repository.GroupRepository;
import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.personal.model.PersonalSchedule;
import com.example.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupWriter {
    private final GroupRepository groupRepository;
    public PersonalSchedule.PersonalScheduleId addMember(Group group, PersonalSchedule personalSchedule){
        return groupRepository.addMember(group,personalSchedule);
    }
    public void updateGroupSchedule(GroupSchedule groupSchedule){groupRepository.updateGroupSchedule(groupSchedule);}
    public Group.GroupId saveGroupSchedule(GroupSchedule groupSchedule, User user){
        return groupRepository.saveGroupSchedule(groupSchedule,user);
    }
}
