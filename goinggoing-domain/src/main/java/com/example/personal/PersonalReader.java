package com.example.personal;

import com.example.group.Group;
import com.example.group.GroupSchedule;
import com.example.routine.Routine;
import com.example.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonalReader {
    private final PersonalRepository personalRepository;
    public PersonalSchedule readPersonalSchedule(PersonalSchedule.PersonalScheduleId personalScheduleId){
        return personalRepository.readPersonalSchedule(personalScheduleId);
    }
    public List<PersonalSchedule> readPersonalSchedules(Group.GroupId groupId){
        return personalRepository.readPersonalSchedules(groupId);
    }
    public Routine readRoutine(Routine.RoutineId routineId){
        return personalRepository.readRoutine(routineId);
    }
}
