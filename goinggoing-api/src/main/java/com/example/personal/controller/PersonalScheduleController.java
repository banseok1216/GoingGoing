package com.example.personal.controller;
import com.example.personal.PersonalSchedule;
import com.example.personal.dto.PersonalScheduleRequest;
import com.example.personal.dto.PersonalScheduleResponse;
import com.example.personal.service.PersonalScheduleService;
import com.example.utils.response.HttpResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class PersonalScheduleController {
    private final PersonalScheduleService personalScheduleService;

    public PersonalScheduleController(PersonalScheduleService personalScheduleService) {
        this.personalScheduleService = personalScheduleService;
    }

    @GetMapping("/personal/schedule")
    public HttpResponse<PersonalScheduleResponse> getPersonalSchedule(
            @RequestParam Long personalScheduleId
    ) {
        PersonalSchedule getPersonalSchedule = personalScheduleService.loadPersonalSchedule(new PersonalSchedule.PersonalScheduleId(personalScheduleId));
        return HttpResponse.success(PersonalScheduleResponse.of(getPersonalSchedule));
    }

    @PutMapping("/personal/schedule")
    public HttpResponse<Object> modifyPersonalSchedule(
            @RequestBody PersonalScheduleRequest request
    ) {
        personalScheduleService.modifyPersonalSchedule(request.toUpdatePersonalSchedule());
        return HttpResponse.successOnly();
    }
}