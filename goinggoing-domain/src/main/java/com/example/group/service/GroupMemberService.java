package com.example.group.service;

import com.example.group.implementation.GroupReader;
import com.example.group.implementation.GroupAppender;
import com.example.group.invite.InviteManager;
import com.example.group.model.Group;
import com.example.personal.model.PersonalSchedule;
import com.example.user.model.User;
import com.example.user.implementation.UserCachedHandler;
import com.example.user.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupMemberService {
    private final GroupReader groupReader;
    private final GroupAppender groupAppender;
    private final UserCachedHandler userCachedHandler;
    private final UserReader userReader;
    private final InviteManager inviteManager;

    public List<User> getGroupMember(Group.GroupId groupId) {
        return groupReader.readGroup(groupId).getPersonalSchedules().stream()
                .map(PersonalSchedule::getUser)
                .collect(Collectors.toList());
    }
    @Transactional
    public PersonalSchedule.PersonalScheduleId addGroupMember(User user,Group.GroupId groupId) {
        Group group = groupReader.readGroup(groupId);
        return groupAppender.addMember(group,user);
    }
    public void inviteGroupMember(User.UserId id, Group.GroupId groupId) {
        User cachedUser = userCachedHandler.get(id.value().toString());
        if (cachedUser == null) {
            User user = userReader.readUser(id);
            userCachedHandler.put(id.value().toString(), user);
            inviteManager.sendInvite(user,groupId);
        } else {
            inviteManager.sendInvite(cachedUser,groupId);
        }
    }
}
