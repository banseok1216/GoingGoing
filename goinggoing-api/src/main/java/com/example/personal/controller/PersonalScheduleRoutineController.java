package com.example.personal.controller;

import com.example.personal.PersonalSchedule;
import com.example.personal.dto.ScheduleRoutineRequest;
import com.example.personal.service.PersonalScheduleRoutineService;
import com.example.routine.Routine;
import com.example.utils.response.HttpResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class PersonalScheduleRoutineController {
    private final PersonalScheduleRoutineService personalScheduleRoutineService;

    public PersonalScheduleRoutineController(PersonalScheduleRoutineService personalScheduleRoutineService) {
        this.personalScheduleRoutineService = personalScheduleRoutineService;
    }

    @PutMapping("/routineSchedule")
    public HttpResponse<Object> modifyPersonalScheduleRoutine(
            @RequestBody ScheduleRoutineRequest.Update request,
            @RequestParam Long personalScheduleId
    ) {
        personalScheduleRoutineService.modifyScheduleRoutine(new PersonalSchedule.PersonalScheduleId(personalScheduleId),request.toUpdateRoutine());
        return HttpResponse.successOnly();
    }

    @PostMapping("/routineSchedule")
    public HttpResponse<Object> createPersonalScheduleRoutine(
            @RequestBody ScheduleRoutineRequest.Create request,
            @RequestParam Long personalScheduleId) {
        personalScheduleRoutineService.createScheduleRoutine(new PersonalSchedule.PersonalScheduleId(personalScheduleId),request.toCreateRoutine());
        return HttpResponse.successOnly();
    }

    @DeleteMapping("/routineSchedule")
    public HttpResponse<Object> deletePersonalScScheduleRoutine(
            @RequestParam Long scheduleRoutineId,
            @RequestParam Long personalScheduleId
    ) {
        personalScheduleRoutineService.deleteScheduleRoutine(new PersonalSchedule.PersonalScheduleId(personalScheduleId),new Routine.RoutineId(scheduleRoutineId));
        return HttpResponse.successOnly();
    }
}
