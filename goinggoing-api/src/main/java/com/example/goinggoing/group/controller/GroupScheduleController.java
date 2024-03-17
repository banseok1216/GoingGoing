package com.example.goinggoing.group.controller;

import com.example.goinggoing.personal.mapstruct.PersonalScheduleMapper;
import com.example.goinggoing.personal.service.PersonalScheduleService;
import com.example.goinggoing.group.mapstruct.GroupScheduleMapper;
import com.example.goinggoing.group.dto.GroupScheduleRequestDto;
import com.example.goinggoing.group.dto.GroupScheduleResponseDto;
import com.example.goinggoing.group.service.GroupScheduleService;
import com.example.goinggoing.user.service.UserService;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.example.goinggoingdomain.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupScheduleController {
    private final GroupScheduleService groupScheduleService;
    private final UserService userService;
    private final PersonalScheduleService personalScheduleService;

    @Autowired
    public GroupScheduleController(GroupScheduleService groupScheduleService, PersonalScheduleService personalScheduleService, UserService userService) {
        this.groupScheduleService = groupScheduleService;
        this.personalScheduleService = personalScheduleService;
        this.userService = userService;
    }

    @GetMapping("/schedule")
    public ResponseEntity<Object> getUserSchedule(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<PersonalSchedule> scheduleList = personalScheduleService.getAllPersonalSchedule(userId);
        List<GroupScheduleResponseDto.GetSchedule> responseDto = PersonalScheduleMapper.INSTANCE.toResponseScheduleListDto(scheduleList);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping("/schedule")
    public ResponseEntity<Object> createSchedule(HttpServletRequest request, @RequestBody GroupScheduleRequestDto groupScheduleRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        GroupSchedule groupSchedule = GroupScheduleMapper.INSTANCE.toEntity(groupScheduleRequestDto);
        groupSchedule = groupScheduleService.createUserSchedule(groupSchedule);
        User user = userService.getUser(userId);
        personalScheduleService.addPersonalSchedule(user, groupSchedule);
        GroupScheduleResponseDto.CreateScheduleId responseDto = GroupScheduleMapper.INSTANCE.toResponseScheduleIdDto(groupSchedule.getScheduleId());
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/schedule")
    public ResponseEntity<Object> updateSchedule(@RequestBody GroupScheduleRequestDto groupScheduleRequestDto) {
        GroupSchedule groupSchedule = GroupScheduleMapper.INSTANCE.toEntity(groupScheduleRequestDto);
        groupScheduleService.updateUserSchedule(groupSchedule);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/schedule")
    public ResponseEntity<Object> deleteSchedule(HttpServletRequest request) {
        groupScheduleService.deleteUserSchedule(Long.valueOf(request.getParameter("scheduleId")));
        return ResponseEntity.ok().body("success");
    }
}

