package com.example.group.implementation;

import com.example.group.repository.GroupRepository;
import com.example.group.model.GroupSchedule;
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
