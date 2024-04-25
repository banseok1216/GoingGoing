package com.example.personal.controller;

import com.example.personal.PersonalSchedule;
import com.example.personal.dto.ScheduleRoutineRequest;
import com.example.personal.service.PersonalScheduleRoutineService;
import com.example.routine.Routine;
import com.example.utils.response.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonalScheduleRoutineController {
    private final PersonalScheduleRoutineService personalScheduleRoutineService;
    @PutMapping("/routineSchedule")
    public HttpResponse<Object> modifyPersonalScheduleRoutine(
            @RequestBody ScheduleRoutineRequest request,
            @RequestParam Long personalScheduleId
    ) {
        personalScheduleRoutineService.modifyScheduleRoutine(new PersonalSchedule.PersonalScheduleId(personalScheduleId),request.mapToUpdateRoutine());
        return HttpResponse.successOnly();
    }

    @PostMapping("/routineSchedule")
    public ResponseEntity<Object> createPersonalScheduleRoutine(
            @RequestBody ScheduleRoutineRequest request,
            @RequestParam Long personalScheduleId) {
        personalScheduleRoutineService.createScheduleRoutine(new PersonalSchedule.PersonalScheduleId(personalScheduleId),request.mapToCreateRoutine());
        return ResponseEntity.ok().body("success");
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
