package com.example.group.service;

import com.example.group.Group;
import com.example.group.GroupReader;
import com.example.group.GroupRemover;
import com.example.group.GroupWriter;
import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupScheduleService {
    private final GroupReader groupReader;
    private final GroupWriter groupWriter;
    private final GroupRemover groupRemover;

    @Transactional
    public void updateUserSchedule(Group group) {
        Group savedGroup = groupReader.read(group.getId());
        Group updatedGroup = Group.withId(savedGroup.getId(),group.getUserId(),group.getGroupSchedule());
        groupWriter.save(updatedGroup);
    }

    public void createGroupSchedule(Group group){
        groupWriter.save(group);
    }
    @Transactional
    public void deleteUserSchedule(Group.GroupId groupId) {
        Group savedGroup = groupReader.read(groupId);
        groupRemover.remove(savedGroup);
    }
}
