package com.example.personal.controller;
import com.example.group.GroupSchedule;
import com.example.personal.PersonalSchedule;
import com.example.personal.dto.PersonalScheduleRequest;
import com.example.personal.service.PersonalScheduleService;
import com.example.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalScheduleController {
    private final PersonalScheduleService personalScheduleService;
    @GetMapping("/personal/schedule")
    public ResponseEntity<Object> getPersonalSchedules(
            @RequestParam Long groupScheduleId
    ) {
        List<PersonalSchedule> personalSchedules = personalScheduleService.loadPersonalSchedules(new GroupSchedule.GroupScheduleId(groupScheduleId));
        return ResponseEntity.ok().body(personalSchedules);
    }

    @PutMapping("/personal/schedule")
    public ResponseEntity<Object> modifyPersonalSchedule(
            @RequestBody PersonalScheduleRequest request
    ) {
        personalScheduleService.modifyPersonalSchedule(request.toUpdatePersonalSchedule());
        return ResponseEntity.ok().body("success");
    }
}