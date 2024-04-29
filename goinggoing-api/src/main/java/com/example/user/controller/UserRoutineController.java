package com.example.user.controller;

import com.example.routine.Routine;
import com.example.routine.RoutineWindow;
import com.example.user.User;
import com.example.user.dto.UserRoutineRequest;
import com.example.user.dto.UserRoutineResponse;
import com.example.user.service.UserRoutineService;
import com.example.utils.response.DefaultId;
import com.example.utils.response.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class UserRoutineController {
    private final UserRoutineService userRoutineService;

    public UserRoutineController(UserRoutineService userRoutineService) {
        this.userRoutineService = userRoutineService;
    }

    @GetMapping("/userRoutine")
    public HttpResponse<List<UserRoutineResponse>> getAllUserRoutine(
            @RequestAttribute Long userId
    ) {
        RoutineWindow routineWindow = userRoutineService.getAllUserRoutine(new User.UserId(userId));
        return HttpResponse.success(UserRoutineResponse.of(routineWindow));
    }

    @DeleteMapping("/userRoutine")
    public HttpResponse<Object> deleteUserRoutine(
            @RequestAttribute Long userId,
            @RequestParam Long userRoutineId
    ) {
        userRoutineService.deleteUserRoutine(new User.UserId(userId),new Routine.RoutineId(userRoutineId));
        return HttpResponse.successOnly();
    }

    @PostMapping("/userRoutine")
    public HttpResponse<DefaultId> createUserRoutine(
            @RequestAttribute Long userId,
            @RequestBody UserRoutineRequest userRoutineRequest
    ) {
        Routine.RoutineId routineId = userRoutineService.createUserRoutine(userRoutineRequest.toRoutine(),new User.UserId(userId));
        return HttpResponse.success(DefaultId.of(routineId.value()));
    }
}
