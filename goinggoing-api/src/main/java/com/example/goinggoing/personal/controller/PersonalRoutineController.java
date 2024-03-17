package com.example.goinggoing.personal.controller;

import com.example.goinggoing.personal.dto.PersonalRoutineRequestDto;
import com.example.goinggoing.personal.dto.PersonalRoutineResponseDto;
import com.example.goinggoing.personal.mapstruct.PersonalRoutineMapper;
import com.example.goinggoing.personal.service.PersonalRoutineService;
import com.example.goinggoing.user.service.UserService;
import com.example.goinggoingdomain.domain.personal.PersonalRoutine;
import com.example.goinggoingdomain.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonalRoutineController {
    PersonalRoutineService personalRoutineService;
    UserService userService;

    @Autowired
    public PersonalRoutineController(
            PersonalRoutineService personalRoutineService, UserService userService) {
        this.personalRoutineService = personalRoutineService;
        this.userService = userService;
    }

    @GetMapping("/userRoutine")
    public ResponseEntity<Object> getPersonalSchedule(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<PersonalRoutine> personalRoutineList = personalRoutineService.getAllUserRoutineInfo(userId);
        List<PersonalRoutineResponseDto.GetUserRoutine> getUserRoutine = PersonalRoutineMapper.INSTANCE.toAllUserRoutine(personalRoutineList);
        return ResponseEntity.ok().body(getUserRoutine);
    }

    @DeleteMapping("/userRoutine")
    public ResponseEntity<Object> deletePersonalSchedule(HttpServletRequest request) {
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
    public ResponseEntity<Object> createPersonalSchedule(HttpServletRequest request, @RequestBody PersonalRoutineRequestDto userRoutineRequestDto) {
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.getUser(userId);
        personalRoutineService.createPersonalRoutineInfo(userRoutineRequestDto, user);
        return ResponseEntity.ok().body("success");
    }
}
