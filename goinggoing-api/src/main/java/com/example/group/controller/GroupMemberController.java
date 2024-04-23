package com.example.group.controller;

import com.example.group.Group;
import com.example.group.dto.GroupMemberResponse;
import com.example.group.mapper.GroupMapper;
import com.example.group.service.GroupMemberService;
import com.example.user.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class GroupMemberController {
    private final GroupMemberService groupMemberService;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/group")
    public ResponseEntity<Object> getGroupMember(HttpServletRequest request) {
        Long groupId = Long.valueOf(request.getParameter("groupId"));
        List<User> users = groupMemberService.getGroupMember(groupMapper.mapToGroupId(groupId));
        GroupMemberResponse getGroup = groupMapper.mapToGroupMemberResponse(users);
        return ResponseEntity.ok().body(getGroup);
    }

    @PostMapping("/group")
    public ResponseEntity<Object> addGroupMember(HttpServletRequest request) {
        Group.GroupId groupId = groupMapper.mapToGroupId(Long.valueOf(request.getParameter("groupId")));
        groupMemberService.addGroupMember(groupId);
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/group/invite")
    public ResponseEntity<Object> inviteGroupMember(HttpServletRequest request) {
        User.UserId userId = userMapper.mapToUserId((Long)request.getAttribute("userId"));
        Group.GroupId groupId = groupMapper.mapToGroupId(Long.valueOf(request.getParameter("groupId")));
        groupMemberService.sendInviteMessage(groupId, userService.getUser(userId));
        return ResponseEntity.ok().body("success");
    }
}
