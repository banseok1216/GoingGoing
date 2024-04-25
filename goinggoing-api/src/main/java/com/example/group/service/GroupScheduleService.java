package com.example.group.service;

import com.example.group.*;
import com.example.user.User;
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
                groupSchedule.getDate(),
                savedGroupSchedule.getPersonalSchedules());
        groupWriter.updateGroupSchedule(newGroupSchedule);
    }

    public void createGroupSchedule(GroupSchedule groupSchedule, User.UserId userId){
        groupWriter.saveGroupSchedule(groupSchedule, userId);
    }
    public List<GroupSchedule> loadGroupSchedules(User.UserId userId){
        return groupReader.readGroupSchedules(userId);
    }
    @Transactional
    public void deleteUserSchedule(GroupSchedule.GroupScheduleId groupScheduleId) {
        groupRemover.removeGroupSchedule(groupScheduleId);
    }
}
