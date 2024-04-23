package com.example.user.controller;

import com.example.group.dto.GroupScheduleResponse;
import com.example.personal.PersonalSchedule;
import com.example.routine.RoutineWindow;
import com.example.user.User;
import com.example.user.dto.UserRoutineRequest;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserRoutineService;
import com.example.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class UserRoutineController {
    private final UserRoutineService userRoutineService;
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/userRoutine")
    public ResponseEntity<Object> getAllUserRoutineInfo(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        RoutineWindow routineWindow = userRoutineService.getAllUserRoutine(userMapper.mapToUserId(userId));
        return ResponseEntity.ok().body(getUserRoutine);
    }

    @DeleteMapping("/userRoutine")
    public ResponseEntity<Object> deleteUserRoutine(
            HttpServletRequest request
    ) {
        Long userRoutineId = Long.valueOf(request.getParameter("userRoutineId"));
        personalRoutineService.deletePersonalRoutineInfo(userRoutineId);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/userRoutineDetail")
    public ResponseEntity<Object> deletePersonalDetailSchedule(HttpServletRequest request) {
        Long userRoutineId = Long.valueOf(request.getParameter("routineDetailId"));
        personalRoutineService.deletePersonalRoutineDetailInfo(userRoutineId);
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/userRoutine")
    public ResponseEntity<Object> createUserSchedule(
            HttpServletRequest request,
            @RequestBody UserRoutineRequest userRoutineRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.getUser(userMapper.mapToUserId(userId));

        personalRoutineService.createPersonalRoutineInfo(, user);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/schedule")
    public ResponseEntity<Object> getUserSchedule(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<PersonalSchedule> scheduleList = personalScheduleService.getAllPersonalSchedule(userId);
        List<GroupScheduleResponse.GetGroupSchedule> responseDto = PersonalScheduleMapper.INSTANCE.toResponseScheduleListDto(scheduleList);
        return ResponseEntity.ok().body(responseDto);
    }
}
