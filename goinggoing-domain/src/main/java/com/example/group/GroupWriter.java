package com.example.group;

import com.example.personal.PersonalSchedule;
import com.example.user.User;
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
