package com.example.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GroupRemover {
    private final GroupRepository groupRepository;
    public void removeGroupSchedule(GroupSchedule.GroupScheduleId groupScheduleId){
        groupRepository.removeGroupSchedule(groupScheduleId);
    }
}
