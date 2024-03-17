package com.example.goinggoing.group.controller;

import com.example.goinggoing.group.dto.GroupMemeberDto;
import com.example.goinggoing.group.mapstruct.GroupMemberMapper;
import com.example.goinggoing.group.dto.GroupMemberResponseDto;
import com.example.goinggoing.group.service.GroupMemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupMemberController {
    private final GroupMemberService groupMemberService;
    @Autowired
    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    @GetMapping("/group")
    public ResponseEntity<Object> getGroupMember(HttpServletRequest request) {
        Long userId = (Long)request.getAttribute("userId");
        Long scheduleId = Long.valueOf(request.getParameter("scheduleId"));
        GroupMemeberDto groupMemeberDto = GroupMemberMapper.INSTANCE.toGroupDto(userId,scheduleId);
        List<GroupMemberResponseDto.GetGroup> getGroup = GroupMemberMapper.INSTANCE.toGetGroupResponseDto(groupMemberService.getGroupMember(groupMemeberDto));
        return ResponseEntity.ok().body(getGroup);
    }

    @PostMapping("/group")
    public ResponseEntity<Object> postGroupMember(HttpServletRequest request) {
        Long userId = (Long)request.getAttribute("userId");
        Long scheduleId = Long.valueOf(request.getParameter("scheduleId"));
        GroupMemeberDto groupMemeberDto = GroupMemberMapper.INSTANCE.toGroupDto(userId,scheduleId);
        GroupMemberResponseDto.AddGroup addGroup = GroupMemberMapper.INSTANCE.toAddGroupResponseDto(groupMemberService.addGroupMember(groupMemeberDto));
        return ResponseEntity.ok().body(addGroup);
    }

    @PostMapping("/group/invite")
    public ResponseEntity<Object> inviteGroupMember(HttpServletRequest request) {
        Long scheduleId = Long.valueOf(request.getParameter("scheduleId"));
        Long memberId = Long.valueOf(request.getParameter("memberId"));
        GroupMemeberDto groupMemeberDto = GroupMemberMapper.INSTANCE.toGroupDto(memberId,scheduleId);
        groupMemberService.sendInviteMessage(groupMemeberDto);
        return ResponseEntity.ok().body("success");
    }
}
