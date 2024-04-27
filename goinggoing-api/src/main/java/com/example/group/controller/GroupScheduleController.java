package com.example.group.controller;

import com.example.group.Group;
import com.example.group.GroupSchedule;
import com.example.group.dto.GroupScheduleRequest;
import com.example.group.dto.GroupScheduleResponse;
import com.example.group.service.GroupScheduleService;
import com.example.user.User;
import com.example.utils.response.DefaultId;
import com.example.utils.response.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class GroupScheduleController {
    private final GroupScheduleService groupScheduleService;

    public GroupScheduleController(GroupScheduleService groupScheduleService) {
        this.groupScheduleService = groupScheduleService;
    }

    @PostMapping("/group/schedule")
    public HttpResponse<DefaultId> createGroupSchedule(
            @RequestBody GroupScheduleRequest request,
            @RequestAttribute Long userId
    ) {
        GroupSchedule groupSchedule = request.toCreateGroupSchedule();
        Group.GroupId groupId = groupScheduleService.createGroupSchedule(groupSchedule,new User.UserId(userId));
        return HttpResponse.success(DefaultId.of(groupId.value()));
    }

    @PutMapping("/group/schedule")
    public HttpResponse<Object> updateGroupSchedule(
            @RequestBody GroupScheduleRequest request
    ) {
        GroupSchedule groupSchedule = request.toModifyGroupSchedule();
        groupScheduleService.modifyGroupSchedule(groupSchedule);
        return HttpResponse.successOnly();
    }

    @DeleteMapping("/group/schedule")
    public HttpResponse<Object> deleteGroupSchedule(
            @RequestParam Long groupScheduleId
    ) {
        groupScheduleService.deleteUserSchedule(new GroupSchedule.GroupScheduleId(groupScheduleId));
        return HttpResponse.successOnly();
    }
}

