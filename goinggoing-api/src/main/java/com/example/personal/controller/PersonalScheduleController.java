package com.example.personal.controller;
import com.example.group.Group;
import com.example.group.GroupSchedule;
import com.example.personal.PersonalSchedule;
import com.example.personal.dto.PersonalScheduleRequest;
import com.example.personal.service.PersonalScheduleService;
import com.example.utils.response.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonalScheduleController {
    private final PersonalScheduleService personalScheduleService;

    public PersonalScheduleController(PersonalScheduleService personalScheduleService) {
        this.personalScheduleService = personalScheduleService;
    }

    @GetMapping("/personal/schedule")
    public ResponseEntity<Object> getPersonalSchedule(
            @RequestParam Long personalScheduleId
    ) {
        PersonalSchedule getPersonalSchedule = personalScheduleService.loadPersonalSchedule(new PersonalSchedule.PersonalScheduleId(personalScheduleId));
        return ResponseEntity.ok().body(getPersonalSchedule);
    }

    @PutMapping("/personal/schedule")
    public HttpResponse<Object> modifyPersonalSchedule(
            @RequestBody PersonalScheduleRequest request
    ) {
        personalScheduleService.modifyPersonalSchedule(request.toUpdatePersonalSchedule());
        return HttpResponse.successOnly();
    }
}