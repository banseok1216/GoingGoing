package com.example.goinggoing.personal.service;

import java.util.List;

import com.example.goinggoing.personal.dto.PersonalScheduleRequestDto;
import com.example.goinggoing.personal.mapstruct.PersonalScheduleMapper;
import com.example.goinggoing.config.redissen.DistributeLock;
import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.personal.PersonalSchedule;
import com.example.goinggoingdomain.domain.personal.repository.PersonalScheduleRepository;
import com.example.goinggoingdomain.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class PersonalScheduleService {
    private final PersonalScheduleRepository personalScheduleRepository;

    public PersonalScheduleService(PersonalScheduleRepository personalScheduleRepository) {
        this.personalScheduleRepository = personalScheduleRepository;
    }

    public List<PersonalSchedule> getAllPersonalSchedule(Long userId) {
        return personalScheduleRepository.findAllByUserUserIdWithSchedule(userId);
    }

    public PersonalSchedule getPersonalSchedule(Long userId, Long scheduleId) {
        return personalScheduleRepository.findByScheduleIdWithScheduleRoutine(userId, scheduleId);
    }

    public PersonalSchedule findPersonalSchedule(Long personalScheduleId) {
        return personalScheduleRepository.findByPersonalScheduleId(personalScheduleId);
    }

    @DistributeLock(key = "#personalScheduleRequestDto.personalScheduleId")
    public void updatePersonalSchedule(PersonalScheduleRequestDto personalScheduleRequestDto) {
        PersonalSchedule getpersonalSchedule = findPersonalSchedule(personalScheduleRequestDto.getPersonalScheduleId());
        getpersonalSchedule.updateInfo(personalScheduleRequestDto.getDuration(), personalScheduleRequestDto.getScheduleDone(), personalScheduleRequestDto.getScheduleStartTime(), personalScheduleRequestDto.getScheduleDoneTime(),personalScheduleRequestDto.getScheduleStart());
    }

    public void addPersonalSchedule(User user, GroupSchedule groupSchedule) {
        PersonalSchedule personalSchedule = PersonalScheduleMapper.INSTANCE.toEntity(user, 0, groupSchedule, false, false, groupSchedule.getScheduleDateTime(), groupSchedule.getScheduleDateTime());
        personalScheduleRepository.saveAndFlush(personalSchedule);
    }

//    public long deletePersonalSchedule(Long userId, Long scheduleId) {
//        PersonalScheduleDto personalScheduleDto = personalScheduleMapper.INSTANCE.toPersonalScheduleDto(scheduleDto.getUserId(), 0, scheduleDto.getScheduleId(),false,false,scheduleDto.getScheduleDateTime(),scheduleDto.getScheduleDateTime());
//        personalschedule personalSchedule = personalScheduleMapper.INSTANCE.toEntity(personalScheduleDto);
//        personalschedule savedPersonalSchedule = personalScheduleRepository.saveAndFlush(personalSchedule);
//        return savedPersonalSchedule.getPersonalScheduleId();
//    }
}
