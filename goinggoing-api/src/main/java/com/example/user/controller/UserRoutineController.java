package com.example.user.controller;

import com.example.group.dto.GroupScheduleResponse;
import com.example.personal.PersonalSchedule;
import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
import com.example.user.User;
import com.example.user.dto.UserResponse;
import com.example.user.dto.UserRoutineRequest;
import com.example.user.dto.UserRoutineResponse;
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

    @GetMapping("/userRoutine")
    public ResponseEntity<Object> getAllUserRoutine(
            @RequestAttribute Long userId
    ) {
        RoutineWindow routineWindow = userRoutineService.getAllUserRoutine(new User.UserId(userId));
        return ResponseEntity.ok().body(UserRoutineResponse.of(routineWindow));
    }

    @DeleteMapping("/userRoutine")
    public ResponseEntity<Object> deleteUserRoutine(
            @RequestAttribute Long userId,
            @RequestParam Long userRoutineId
    ) {
        userRoutineService.deleteUserRoutine(new User.UserId(userId),new Routine.RoutineId(userRoutineId));
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/userRoutine")
    public ResponseEntity<Object> createUserRoutine(
            @RequestAttribute Long userId,
            @RequestBody UserRoutineRequest userRoutineRequest
    ) {
        userRoutineService.createUserRoutine(userRoutineRequest.toRoutine(),new User.UserId(userId));
        return ResponseEntity.ok().body("success");
    }
}
