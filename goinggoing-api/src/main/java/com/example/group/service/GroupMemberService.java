package com.example.group.service;

import com.example.group.Group;
import com.example.group.GroupReader;
import com.example.group.GroupWriter;
import com.example.redis.device.DeviceToken;
import com.example.redis.device.repository.DeviceTokenRepository;
import com.example.redis.message.MessageDto;
import com.example.redis.pub.RedisPublisher;
import com.example.user.User;
import com.example.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMemberService {
    private final UserReader userReader;
    private final GroupReader groupReader;
    private final GroupWriter groupWriter;
    private final DeviceTokenRepository deviceTokenRepository;
    private final RedisPublisher redisPublisher;

    public List<User> getGroupMember(Group.GroupId groupId) {
        return groupReader.readGroupUsers(groupId);
    }

    public void addGroupMember(User.UserId userId,Group.GroupId groupId) {
        Group group = groupReader.readGroup(groupId);
        group.getUsers().add(userReader.readUser(userId));
        groupWriter.saveGroup(group);
    }

    public void sendInviteMessage(Group.GroupId groupId, User user) {
        DeviceToken deviceToken =deviceTokenRepository.findByUserId(String.valueOf(user.getId().getValue()));
        Group group = groupReader.readGroup(groupId);
        MessageDto messageDto = new MessageDto();
        messageDto.setDeviceToken(deviceToken.getDeviceToken());
        messageDto.setScheduleId(group.getId().value());
        messageDto.setContent(user.getUserNickname() + "님이 일정에 초대했어요");
        redisPublisher.publish(messageDto);
    }
}
