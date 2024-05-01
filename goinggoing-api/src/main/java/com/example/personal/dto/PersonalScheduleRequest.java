package com.example.personal.dto;

import com.example.personal.model.PersonalSchedule;

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
                null,null);
    }
}
