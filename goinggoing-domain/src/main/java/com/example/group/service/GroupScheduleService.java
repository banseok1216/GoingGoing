package com.example.group.service;

import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupScheduleService {
    private final GroupReader groupReader;
    private final GroupWriter groupWriter;
    private final GroupRemover groupRemover;

    @Transactional
    public void modifyGroupSchedule(GroupSchedule groupSchedule) {
        GroupSchedule savedGroupSchedule = groupReader.readGroupSchedule(groupSchedule.getId());
        GroupSchedule newGroupSchedule = GroupSchedule.withId(
                savedGroupSchedule.getId(),
                groupSchedule.getName(),
                groupSchedule.getDescription(),
                groupSchedule.getLocation(),
                groupSchedule.getDate());
        groupWriter.updateGroupSchedule(newGroupSchedule);
    }

    public Group.GroupId createGroupSchedule(GroupSchedule groupSchedule, User user){
        return groupWriter.saveGroupSchedule(groupSchedule, user);
    }

    public List<Group> loadGroup(User user){
        return groupReader.readGroupList(user);
    }
    @Transactional
    public void deleteUserSchedule(GroupSchedule.GroupScheduleId groupScheduleId) {
        groupRemover.removeGroupSchedule(groupScheduleId);
    }
}
