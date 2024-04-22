package com.example.group.controller;

import com.example.group.Group;
import com.example.group.dto.GroupScheduleRequest;
import com.example.group.mapper.GroupMapper;
import com.example.group.service.GroupScheduleService;
import com.example.user.User;
import com.example.user.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupScheduleController {
    private final GroupScheduleService groupScheduleService;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;


    @PostMapping("/group/schedule")
    public ResponseEntity<Object> createSchedule(HttpServletRequest request, @RequestBody GroupScheduleRequest groupScheduleRequest) {
        User.UserId userId = userMapper.mapToUserId((Long)request.getAttribute("userId"));
        Group group = groupMapper.mapToCreateGroupSchedule(userId,groupScheduleRequest);
        groupScheduleService.createGroupSchedule(group);
        return ResponseEntity.ok().body("success");
    }

    @PutMapping("/group/schedule")
    public ResponseEntity<Object> updateSchedule(HttpServletRequest request,@RequestBody GroupScheduleRequest groupScheduleRequestDto) {
        User.UserId userId = userMapper.mapToUserId((Long)request.getAttribute("userId"));
        Group group = groupMapper.mapToUpdateGroupSchedule(userId,groupScheduleRequestDto);
        groupScheduleService.updateUserSchedule(group);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/group/schedule")
    public ResponseEntity<Object> deleteSchedule(@RequestParam Long groupId) {
        groupScheduleService.deleteUserSchedule(groupMapper.mapToGroupId(groupId));
        return ResponseEntity.ok().body("success");
    }
}

