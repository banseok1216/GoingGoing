package com.example.group.service;

import com.example.group.model.Group;
import com.example.personal.model.PersonalSchedule;
import com.example.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupMemberService {
    private final GroupReader groupReader;
    private final GroupWriter groupWriter;

    public List<User> getGroupMember(Group.GroupId groupId) {
        return groupReader.readGroup(groupId).getPersonalSchedules().stream()
                .map(PersonalSchedule::getUser)
                .collect(Collectors.toList());
    }
    @Transactional
    public PersonalSchedule.PersonalScheduleId addGroupMember(User user,Group.GroupId groupId) {
        Group group = groupReader.readGroup(groupId);
        return groupWriter.addMember(group,PersonalSchedule.initialized(user,group.getGroupSchedule()));
    }
}
