package com.example.group.controller;

import com.example.group.model.Group;
import com.example.group.model.GroupSchedule;
import com.example.group.dto.GroupScheduleRequest;
import com.example.group.service.GroupScheduleService;
import com.example.user.domain.User;
import com.example.user.service.UserService;
import com.example.utils.response.DefaultId;
import com.example.utils.response.HttpResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class GroupScheduleController {
    private final GroupScheduleService groupScheduleService;
    private final UserService userService;

    public GroupScheduleController(GroupScheduleService groupScheduleService, UserService userService) {
        this.groupScheduleService = groupScheduleService;
        this.userService = userService;
    }

    @PostMapping("/group/schedule")
    public HttpResponse<DefaultId> createGroupSchedule(
            @RequestBody GroupScheduleRequest.Create request,
            @RequestAttribute Long userId
    ) {
        GroupSchedule groupSchedule = request.toCreateGroupSchedule();
        User user = userService.getUser(new User.UserId(userId));
        Group.GroupId groupId = groupScheduleService.createGroupSchedule(groupSchedule,user);
        return HttpResponse.success(DefaultId.of(groupId.value()));
    }

    @PutMapping("/group/schedule")
    public HttpResponse<Object> updateGroupSchedule(
            @RequestBody GroupScheduleRequest.Update request
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

