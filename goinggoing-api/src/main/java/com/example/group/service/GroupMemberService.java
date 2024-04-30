package com.example.group.service;

import com.example.group.Group;
import com.example.group.GroupReader;
import com.example.group.GroupWriter;
import com.example.personal.PersonalSchedule;
import com.example.redis.message.MessageDto;
import com.example.redis.pub.RedisPublisher;
import com.example.user.User;
import com.example.user.UserReader;
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
    private final RedisPublisher redisPublisher;

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

    public void sendInviteMessage(Group.GroupId groupId, User user) {
        Group group = groupReader.readGroup(groupId);
        MessageDto messageDto = new MessageDto();
        messageDto.setDeviceToken(user.getDeviceToken());
        messageDto.setScheduleId(group.getId().value());
        messageDto.setContent(user.getUserNickname() + "님이 일정에 초대했어요");
        redisPublisher.publish(messageDto);
    }
}
