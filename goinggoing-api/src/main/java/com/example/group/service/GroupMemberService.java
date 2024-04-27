package com.example.group.service;

import com.example.group.Group;
import com.example.group.GroupReader;
import com.example.group.GroupWriter;
import com.example.personal.PersonalSchedule;
import com.example.redis.device.DeviceToken;
import com.example.redis.device.repository.DeviceTokenRepository;
import com.example.redis.message.MessageDto;
import com.example.redis.pub.RedisPublisher;
import com.example.user.User;
import com.example.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupMemberService {
    private final UserReader userReader;
    private final GroupReader groupReader;
    private final GroupWriter groupWriter;
    private final DeviceTokenRepository deviceTokenRepository;
    private final RedisPublisher redisPublisher;

    public List<User> getGroupMember(Group.GroupId groupId) {
        return groupReader.readGroup(groupId).getPersonalSchedules().stream()
                .map(PersonalSchedule::getUser)
                .collect(Collectors.toList());
    }

    public PersonalSchedule.PersonalScheduleId addGroupMember(User.UserId userId,Group.GroupId groupId) {
        Group group = groupReader.readGroup(groupId);
        User user = userReader.readUser(userId);
        return groupWriter.addMember(group,PersonalSchedule.initialized(user,group.getGroupSchedule()));
    }

    public void sendInviteMessage(Group.GroupId groupId, User user) {
        DeviceToken deviceToken =deviceTokenRepository.findByUserId(String.valueOf(user.getId().value()));
        Group group = groupReader.readGroup(groupId);
        MessageDto messageDto = new MessageDto();
        messageDto.setDeviceToken(deviceToken.getDeviceToken());
        messageDto.setScheduleId(group.getId().value());
        messageDto.setContent(user.getUserNickname() + "님이 일정에 초대했어요");
        redisPublisher.publish(messageDto);
    }
}
