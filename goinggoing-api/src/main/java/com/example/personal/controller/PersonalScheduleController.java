//package com.example.goinggoing.personal.controller;
//
//import com.example.personal.dto.PersonalScheduleRequestDto;
//import com.example.personal.dto.PersonalScheduleResponseDto;
//import com.example.goinggoing.personal.mapper.PersonalScheduleMapper;
//import com.example.goinggoing.personal.service.PersonalScheduleService;
//import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class PersonalScheduleController {
//    private final PersonalScheduleService personalScheduleService;
//    @Autowired
//    public PersonalScheduleController(PersonalScheduleService personalScheduleService) {
//        this.personalScheduleService = personalScheduleService;
//    }
//    @GetMapping("/personalSchedule")
//    public ResponseEntity<Object> getPersonalSchedule(HttpServletRequest request) {
//        Long userId = (Long)request.getAttribute("userId");
//        Long scheduleId = Long.valueOf(request.getParameter("scheduleId"));
//        PersonalSchedule gettedpersonalSchedule = personalScheduleService.getPersonalSchedule(userId,scheduleId);
//        PersonalScheduleResponseDto.GetPersonalSchedule personalScheduleResponseDto = PersonalScheduleMapper.INSTANCE.toPersonalScheduleResponseDto(gettedpersonalSchedule);
//        return ResponseEntity.ok().body(personalScheduleResponseDto);
//    }
//    @GetMapping("/memberSchedule")
//    public ResponseEntity<Object> getMemberSchedule(HttpServletRequest request) {
//        Long userId = Long.valueOf(request.getParameter("memberId"));
//        Long scheduleId = Long.valueOf(request.getParameter("scheduleId"));
//        PersonalSchedule gettedpersonalSchedule = personalScheduleService.getPersonalSchedule(userId,scheduleId);
//        PersonalScheduleResponseDto.GetPersonalSchedule personalScheduleResponseDto = PersonalScheduleMapper.INSTANCE.toPersonalScheduleResponseDto(gettedpersonalSchedule);
//        return ResponseEntity.ok().body(personalScheduleResponseDto);
//    }
//
//    @PutMapping("/personalSchedule")
//    public ResponseEntity<Object> updatePersonalSchedule(@RequestBody PersonalScheduleRequestDto personalScheduleRequestDto) {
//        personalScheduleService.updatePersonalSchedule(personalScheduleRequestDto);
//        return ResponseEntity.ok().body("success");
//    }
//}