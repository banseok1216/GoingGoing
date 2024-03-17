package com.example.goinggoing.group.service;

import com.example.goinggoing.group.mapstruct.GroupMemberMapper;
import com.example.goinggoing.group.dto.GroupMemeberDto;
import com.example.goinggoing.redis.device.DeviceToken;
import com.example.goinggoing.redis.device.repository.DeviceTokenRepository;
import com.example.goinggoing.redis.message.MessageDto;
import com.example.goinggoing.redis.pub.RedisPublisher;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.group.repository.GroupScheduleRepository;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.example.goinggoingdomain.domain.personal.repository.PersonalScheduleRepository;
import com.example.goinggoingdomain.domain.user.User;
import com.example.goinggoingdomain.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberService {
    private final PersonalScheduleRepository personalScheduleRepository;
    private final GroupScheduleRepository groupScheduleRepository;
    private final UserRepository userRepository;
    private final DeviceTokenRepository deviceTokenRepository;
    private final RedisPublisher redisPublisher;


    public GroupMemberService(PersonalScheduleRepository personalScheduleRepository, GroupScheduleRepository groupScheduleRepository, UserRepository userRepository, DeviceTokenRepository deviceTokenRepository, RedisPublisher redisPublisher) {
        this.personalScheduleRepository = personalScheduleRepository;
        this.groupScheduleRepository = groupScheduleRepository;
        this.userRepository = userRepository;
        this.deviceTokenRepository = deviceTokenRepository;
        this.redisPublisher = redisPublisher;
    }

    public List<User> getGroupMember(GroupMemeberDto groupMemeberDto) {
        List<PersonalSchedule> groupPersonalSchedules = personalScheduleRepository.findAllByScheduleScheduleId(groupMemeberDto.getScheduleId());
        return groupPersonalSchedules.stream()
                .map(PersonalSchedule::getUser)
                .toList();
    }

    public Long addGroupMember(GroupMemeberDto groupMemeberDto) {
        GroupSchedule groupSchedule = groupScheduleRepository.findByScheduleId(groupMemeberDto.getScheduleId());
        User user = userRepository.findByUserId(groupMemeberDto.getUserId());
        PersonalSchedule perSonalSchedule = GroupMemberMapper.INSTANCE.toEntity(user, groupSchedule);
        return personalScheduleRepository.save(perSonalSchedule).getPersonalScheduleId();
    }

    public void sendInviteMessage(GroupMemeberDto groupMemeberDto) {
        User user = userRepository.findByUserId(groupMemeberDto.getUserId());
        DeviceToken deviceToken =deviceTokenRepository.findByUserId(String.valueOf(user.getUserId()));
        MessageDto messageDto = new MessageDto();
        messageDto.setDeviceToken(deviceToken.getDeviceToken());
        messageDto.setScheduleId(groupMemeberDto.getScheduleId());
        messageDto.setContent(user.getUserNickname() + "님이 일정에 초대했어요");
        redisPublisher.publish(messageDto);
    }
}
