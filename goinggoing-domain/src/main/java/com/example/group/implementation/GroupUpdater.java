package com.example.group.implementation;

import com.example.group.model.GroupSchedule;
import com.example.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupUpdater {
    private final GroupRepository groupRepository;
    public void updateGroupSchedule(GroupSchedule source,GroupSchedule target){groupRepository.updateGroupSchedule(source.update(target));}
}
