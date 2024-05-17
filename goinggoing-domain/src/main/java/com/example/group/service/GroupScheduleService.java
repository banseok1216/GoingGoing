package com.example.group.service;

import com.example.group.implementation.GroupReader;
import com.example.group.implementation.GroupRemover;
import com.example.group.implementation.GroupAppender;
import com.example.group.implementation.GroupUpdater;
import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupScheduleService {
    private final GroupReader groupReader;
    private final GroupAppender groupAppender;
    private final GroupRemover groupRemover;
    private final GroupUpdater groupUpdater;

    @Transactional
    public void modifyGroupSchedule(GroupSchedule groupSchedule) {
        GroupSchedule savedGroupSchedule = groupReader.readGroupSchedule(groupSchedule.getId());
        groupUpdater.updateGroupSchedule(groupSchedule,savedGroupSchedule);
    }

    public Group.GroupId createGroupSchedule(GroupSchedule groupSchedule, User user){
        return groupAppender.saveGroupSchedule(groupSchedule, user);
    }
    public List<Group> loadGroup(User user){
        return groupReader.readGroupList(user);
    }
    @Transactional
    public void deleteUserSchedule(GroupSchedule.GroupScheduleId groupScheduleId) {
        groupRemover.removeGroupSchedule(groupScheduleId);
    }
}
