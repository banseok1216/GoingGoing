package com.example.user.controller;

import com.example.routine.model.Routine;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import com.example.user.dto.UserRoutineRequest;
import com.example.user.dto.UserRoutineResponse;
import com.example.user.model.UserRoutineWindow;
import com.example.user.service.UserRoutineService;
import com.example.user.service.UserService;
import com.example.utils.response.DefaultId;
import com.example.utils.response.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class UserRoutineController {
    private final UserRoutineService userRoutineService;
    private final UserService userService;

    public UserRoutineController(UserRoutineService userRoutineService, UserService userService) {
        this.userRoutineService = userRoutineService;
        this.userService = userService;
    }

    @GetMapping("/userRoutine")
    public HttpResponse<List<UserRoutineResponse>> getAllUserRoutine(
            @RequestAttribute Long userId
    ) {
        UserRoutineWindow routineWindow = userRoutineService.getAllUserRoutine(userService.getUser(new User.UserId(userId)));
        return HttpResponse.success(UserRoutineResponse.of(routineWindow));
    }

    @DeleteMapping("/userRoutine")
    public HttpResponse<Object> deleteUserRoutine(
            @RequestAttribute Long userId,
            @RequestParam Long userRoutineId
    ) {
        userRoutineService.deleteUserRoutine(userService.getUser(new User.UserId(userId)),new Routine.RoutineId(userRoutineId));
        return HttpResponse.successOnly();
    }

    @PostMapping("/userRoutine")
    public HttpResponse<DefaultId> createUserRoutine(
            @RequestAttribute Long userId,
            @RequestBody UserRoutineRequest userRoutineRequest
    ) {
        Routine.RoutineId routineId = userRoutineService.createUserRoutine(userRoutineRequest.toRoutine(),userService.getUser(new User.UserId(userId)));
        return HttpResponse.success(DefaultId.of(routineId.value()));
    }
}
