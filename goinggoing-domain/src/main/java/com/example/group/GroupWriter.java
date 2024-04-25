package com.example.group;

import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupWriter {
    private final GroupRepository groupRepository;
    public void saveGroup(Group group){
        groupRepository.save(group);
    }
    public void updateGroupSchedule(GroupSchedule groupSchedule){groupRepository.updateGroupSchedule(groupSchedule);}
    public void saveGroupSchedule(GroupSchedule groupSchedule, User.UserId userId){groupRepository.saveGroupSchedule(groupSchedule,userId);}

}
