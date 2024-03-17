package com.example.goinggoing.personal.controller;

import com.example.goinggoing.personal.mapstruct.PersonalScheduleRoutineMapper;
import com.example.goinggoing.personal.dto.PersonalScheduleRoutineRequestDto;
import com.example.goinggoing.personal.dto.PersonalScheduleRoutineResponseDto;
import com.example.goinggoing.personal.service.PersonalScheduleRoutineService;
import com.example.goinggoingdomain.domain.personal.PersonalScheduleRoutine;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Controller
public class PersonalScheduleRoutineController {
    PersonalScheduleRoutineService personalScheduleRoutineService;
    @Autowired
    public PersonalScheduleRoutineController(PersonalScheduleRoutineService personalScheduleRoutineService) {
        this.personalScheduleRoutineService = personalScheduleRoutineService;
    }

    @PutMapping("/routineSchedule")
    public ResponseEntity<Object> updatePersonalScheduleRoutine(@RequestBody PersonalScheduleRoutineRequestDto.updateRoutineDto updateRoutineDto) {
        PersonalScheduleRoutine personalScheduleRoutine = PersonalScheduleRoutineMapper.INSTANCE.toUpdateEntity(updateRoutineDto);
        personalScheduleRoutineService.updateScheduleRoutine(personalScheduleRoutine);
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/routineSchedule")
    public ResponseEntity<Object> createPersonalScheduleRoutine(@RequestBody PersonalScheduleRoutineRequestDto.createRoutineDto createRoutineDto) {
        PersonalScheduleRoutine personalScheduleRoutine = PersonalScheduleRoutineMapper.INSTANCE.toCreateEntity(createRoutineDto);
        Long scheduleRoutineId = personalScheduleRoutineService.createScheduleRoutine(personalScheduleRoutine);
        PersonalScheduleRoutineResponseDto.CreateScheduleId responseDto = PersonalScheduleRoutineMapper.INSTANCE.toCreateResponseDto(scheduleRoutineId);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/routineSchedule")
    public ResponseEntity<Object> deletePersonalScScheduleRoutine(HttpServletRequest request) {
        personalScheduleRoutineService.deleteScheduleRoutine(Long.valueOf(request.getParameter("scheduleRoutineId")));
        return ResponseEntity.ok().body("success");
    }
}
