package com.example.group.controller;

import com.example.group.Group;
import com.example.group.dto.GroupMemberResponse;
import com.example.group.service.GroupMemberService;
import com.example.personal.PersonalSchedule;
import com.example.user.User;
import com.example.user.service.UserService;
import com.example.utils.response.DefaultId;
import com.example.utils.response.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class GroupMemberController {
    private final GroupMemberService groupMemberService;
    private final UserService userService;

    public GroupMemberController(GroupMemberService groupMemberService, UserService userService) {
        this.groupMemberService = groupMemberService;
        this.userService = userService;
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
        groupMemberService.sendInviteMessage(
                new Group.GroupId(groupId),
                userService.getUser(new User.UserId(userId)));
        return HttpResponse.successOnly();
    }
}
