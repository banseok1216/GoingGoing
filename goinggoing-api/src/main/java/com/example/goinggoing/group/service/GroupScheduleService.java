package com.example.goinggoing.group.service;

import com.example.goinggoingdomain.domain.group.GroupSchedule;
import com.example.goinggoingdomain.domain.group.repository.GroupScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupScheduleService {
    private final GroupScheduleRepository groupScheduleRepository;

    public GroupScheduleService(GroupScheduleRepository groupScheduleRepository) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    public GroupSchedule createUserSchedule(GroupSchedule groupSchedule) {
        return groupScheduleRepository.save(groupSchedule);
    }

    @Transactional
    public void updateUserSchedule(GroupSchedule groupSchedule) {
        GroupSchedule savedGroupSchedule = groupScheduleRepository.findByScheduleId(groupSchedule.getScheduleId());
        savedGroupSchedule.updateScheduleNameTimeLocation(groupSchedule.getScheduleDateTime(), groupSchedule.getScheduleLocation(), groupSchedule.getScheduleName());
    }
    @Transactional
    public void deleteUserSchedule(Long scheduleId) {
        GroupSchedule savedGroupSchedule = groupScheduleRepository.findByScheduleId(scheduleId);
        groupScheduleRepository.deleteByScheduleId(savedGroupSchedule.getScheduleId());
    }
}
