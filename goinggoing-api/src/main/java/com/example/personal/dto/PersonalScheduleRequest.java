package com.example.personal.dto;

import com.example.personal.PersonalSchedule;
import com.example.routine.RoutineWindow;
import com.example.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record PersonalScheduleRequest(
        Integer personalDuration,
        Long personalScheduleId
) {
    public PersonalSchedule toUpdatePersonalSchedule(){
        return PersonalSchedule.withId(new PersonalSchedule.PersonalScheduleId(personalScheduleId),
                personalDuration,
                null,
                null,
                null,
                null);
    }
}
