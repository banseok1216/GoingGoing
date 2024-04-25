package com.example.group.controller;

import com.example.group.Group;
import com.example.group.dto.GroupMemberResponse;
import com.example.group.service.GroupMemberService;
import com.example.personal.PersonalSchedule;
import com.example.user.User;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class GroupMemberController {
    private final GroupMemberService groupMemberService;
    private final UserService userService;

    @GetMapping("/group")
    public ResponseEntity<Object> getGroupMember(
            @RequestParam Long groupId
    ) {
        List<User> users = groupMemberService.getGroupMember(new Group.GroupId(groupId));
        return ResponseEntity.ok().body(GroupMemberResponse.of(users));
    }

    @PostMapping("/group")
    public ResponseEntity<Object> appendGroupMember(
            @RequestAttribute Long userId,
            @RequestParam Long groupId
    ) {
        groupMemberService.addGroupMember(new User.UserId(userId), new Group.GroupId(groupId));
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/group/invite")
    public ResponseEntity<Object> inviteGroupMember(
            @RequestAttribute Long userId,
            @RequestParam Long groupId
    ) {
        groupMemberService.sendInviteMessage(
                new Group.GroupId(groupId),
                userService.getUser(new User.UserId(userId)));
        return ResponseEntity.ok().body("success");
    }
}
