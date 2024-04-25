package com.example.group.controller;

import com.example.group.GroupSchedule;
import com.example.group.dto.GroupScheduleRequest;
import com.example.group.dto.GroupScheduleResponse;
import com.example.group.service.GroupScheduleService;
import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class GroupScheduleController {
    private final GroupScheduleService groupScheduleService;

    @PostMapping("/group/schedule")
    public ResponseEntity<Object> createGroupSchedule(
            @RequestBody GroupScheduleRequest request,
            @RequestAttribute Long userId
    ) {
        GroupSchedule groupSchedule = request.toCreateGroupSchedule();
        groupScheduleService.createGroupSchedule(groupSchedule,new User.UserId(userId));
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/group/schedule")
    public ResponseEntity<Object> getAllGroupSchedule(
            @RequestAttribute Long userId
    ) {
        List<GroupSchedule> groupSchedules = groupScheduleService.loadGroupSchedules(new User.UserId(userId));
        return ResponseEntity.ok().body(GroupScheduleResponse.of(groupSchedules));
    }

    @PutMapping("/group/schedule")
    public ResponseEntity<Object> updateGroupSchedule(
            @RequestBody GroupScheduleRequest request
    ) {
        GroupSchedule groupSchedule = request.toModifyGroupSchedule();
        groupScheduleService.modifyGroupSchedule(groupSchedule);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/group/schedule")
    public ResponseEntity<Object> deleteGroupSchedule(
            @RequestParam Long groupScheduleId
    ) {
        groupScheduleService.deleteUserSchedule(new GroupSchedule.GroupScheduleId(groupScheduleId));
        return ResponseEntity.ok().body("success");
    }
}

