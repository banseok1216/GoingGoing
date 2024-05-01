package com.example.scheduling;

import com.example.group.model.Group;
import com.example.group.service.GroupReader;
import com.example.personal.model.PersonalSchedule;
import com.example.personal.service.PersonalWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulingService {
    private final PersonalWriter personalWriter;
    private final GroupReader groupReader;


    public List<Group> getScheduleDoneTargetList(){
        return groupReader.readPersonalSchedulesWithNotDone();
    }
    public List<Group> getScheduleStartTargetList(){
        return groupReader.readPersonalSchedulesWithNotStart();
    }

    @Transactional
    public Group modifyScheduleDoneNotifications(Group group) {
        PersonalSchedule personalSchedule = group.getPersonalSchedules().get(0);
        personalSchedule.updateStatusAndTime();
        personalSchedule.updateEndNotified();
        personalWriter.modifyPersonalSchedule(personalSchedule);
        return groupReader.readGroup(group.getId());
    }

    @Transactional
    public Group modifyScheduleStartNotifications(Group group) {
        PersonalSchedule personalSchedule = group.getPersonalSchedules().get(0);
        personalSchedule.updateStatusAndTime();
        personalSchedule.updateStartNotified();
        personalWriter.modifyPersonalSchedule(personalSchedule);
        return groupReader.readGroup(group.getId());
    }
}
