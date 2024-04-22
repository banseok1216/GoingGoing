//package com.example.personal.service;
//
//import java.util.List;
//
//import com.example.personal.PersonalReader;
//import com.example.personal.PersonalSchedule;
//import com.example.personal.PersonalWriter;
//import com.example.personal.dto.PersonalScheduleRequestDto;
//import com.example.user.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class PersonalScheduleService {
//    private final PersonalReader personalReader;
//    private final PersonalWriter personalWriter;
//    public List<PersonalSchedule> getAllPersonalSchedule(User.UserId userId) {
//        return personalReader.readByUserId(userId);
//    }
//
//    public PersonalSchedule getPersonalSchedule(Long userId, Long scheduleId) {
//        return personalScheduleRepository.findByScheduleIdWithScheduleRoutine(userId, scheduleId);
//    }
//
//    public PersonalSchedule findPersonalSchedule(Long personalScheduleId) {
//        return personalScheduleRepository.findByPersonalScheduleId(personalScheduleId);
//    }
//
//    public void updatePersonalSchedule(PersonalScheduleRequestDto personalScheduleRequestDto) {
//        PersonalSchedule getpersonalSchedule = findPersonalSchedule(personalScheduleRequestDto.getPersonalScheduleId());
//        getpersonalSchedule.updateInfo(personalScheduleRequestDto.getDuration(), personalScheduleRequestDto.getScheduleDone(), personalScheduleRequestDto.getScheduleStartTime(), personalScheduleRequestDto.getScheduleDoneTime(),personalScheduleRequestDto.getScheduleStart());
//    }
//
//    public void addPersonalSchedule(User user, GroupSchedule groupSchedule) {
//        PersonalSchedule personalSchedule = PersonalScheduleMapper.INSTANCE.toEntity(user, 0, groupSchedule, false, false, groupSchedule.getScheduleDateTime(), groupSchedule.getScheduleDateTime());
//        personalScheduleRepository.saveAndFlush(personalSchedule);
//    }
//
////    public long deletePersonalSchedule(Long userId, Long scheduleId) {
////        PersonalScheduleDto personalScheduleDto = personalScheduleMapper.INSTANCE.toPersonalScheduleDto(scheduleDto.getUserId(), 0, scheduleDto.getScheduleId(),false,false,scheduleDto.getScheduleDateTime(),scheduleDto.getScheduleDateTime());
////        personalschedule personalSchedule = personalScheduleMapper.INSTANCE.toEntity(personalScheduleDto);
////        personalschedule savedPersonalSchedule = personalScheduleRepository.saveAndFlush(personalSchedule);
////        return savedPersonalSchedule.getPersonalScheduleId();
////    }
//}
