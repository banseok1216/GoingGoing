//package com.example.goinggoing.personal.service;
//
//import com.example.goinggoingdomain.domain.personal.PersonalScheduleRoutine;
//import com.example.goinggoingdomain.domain.personal.repository.PersonalScheduleRoutineRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class PersonalScheduleRoutineService {
//    private final PersonalScheduleRoutineRepository personalScheduleRoutineRepository;
//
//
//    public PersonalScheduleRoutineService(PersonalScheduleRoutineRepository personalScheduleRoutineRepository) {
//        this.personalScheduleRoutineRepository = personalScheduleRoutineRepository;
//    }
//    @Transactional
//    public void updateScheduleRoutine(PersonalScheduleRoutine personalScheduleRoutine) {
//        PersonalScheduleRoutine savedPersonalScheduleRoutine = personalScheduleRoutineRepository.findByScheduleRoutineId(personalScheduleRoutine.getScheduleRoutineId());
//        savedPersonalScheduleRoutine.updateScheduleRoutineInfo(personalScheduleRoutine);
//    }
//
//    public Long createScheduleRoutine(PersonalScheduleRoutine personalScheduleRoutine) {
//        return personalScheduleRoutineRepository.save(personalScheduleRoutine).getScheduleRoutineId();
//    }
//    @Transactional
//    public void deleteScheduleRoutine(Long scheduleRoutineId) {
//        PersonalScheduleRoutine savedPersonalScheduleRoutine = personalScheduleRoutineRepository.findByScheduleRoutineId(scheduleRoutineId);
//        personalScheduleRoutineRepository.deleteByScheduleRoutineId(savedPersonalScheduleRoutine.getScheduleRoutineId());
//    }
//}
