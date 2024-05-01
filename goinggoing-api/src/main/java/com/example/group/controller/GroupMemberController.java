package com.example.group.controller;

import com.example.group.model.Group;
import com.example.group.dto.GroupMemberResponse;
import com.example.group.service.GroupMemberService;
import com.example.personal.model.PersonalSchedule;
import com.example.redis.message.Message;
import com.example.redis.pub.RedisPublisher;
import com.example.user.domain.User;
import com.example.user.service.UserService;
import com.example.utils.response.DefaultId;
import com.example.utils.response.HttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class GroupMemberController {
    private final GroupMemberService groupMemberService;
    private final UserService userService;
    private final RedisPublisher redisPublisher;

    public GroupMemberController(GroupMemberService groupMemberService, UserService userService, RedisPublisher redisPublisher) {
        this.groupMemberService = groupMemberService;
        this.userService = userService;
        this.redisPublisher = redisPublisher;
    }

    @GetMapping("/group")
    public HttpResponse<List<GroupMemberResponse>> getGroupMember(
            @RequestParam Long groupId
    ) {
        List<User> users = groupMemberService.getGroupMember(new Group.GroupId(groupId));
        return HttpResponse.success(GroupMemberResponse.of(users));
    }

    @PostMapping("/group")
    public HttpResponse<Object> appendGroupMember(
            @RequestAttribute Long userId,
            @RequestParam Long groupId
    ) {
       User user = userService.getUser(new User.UserId(userId));
        PersonalSchedule.PersonalScheduleId personalScheduleId = groupMemberService.addGroupMember(user, new Group.GroupId(groupId));
        return HttpResponse.success(DefaultId.of(personalScheduleId.value()));
    }

    @PostMapping("/group/invite")
    public HttpResponse<Object> inviteGroupMember(
            @RequestAttribute Long userId,
            @RequestParam Long groupId
    ) {
        User user = userService.getUser(new User.UserId(userId));
        Message message = Message.of(user,new Group.GroupId(groupId));
        redisPublisher.publish(message);
        return HttpResponse.successOnly();
    }
}
