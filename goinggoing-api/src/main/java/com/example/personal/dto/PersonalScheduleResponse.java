package com.example.personal.dto;

import com.example.personal.model.PersonalSchedule;

import java.time.LocalDateTime;
import java.util.List;

public record PersonalScheduleResponse(
        Long personalScheduleId,
        Integer duration,
        Boolean scheduleStart,
        Boolean scheduleDone,

        LocalDateTime scheduleStartTime,
        LocalDateTime scheduleDoneTime,
        List<ScheduleRoutineResponse> routineResponseList
) {
    public static PersonalScheduleResponse of(PersonalSchedule personalSchedule) {
        return new PersonalScheduleResponse(
                personalSchedule.getId().value(),
                personalSchedule.getPersonalDuration(),
                personalSchedule.getPersonalScheduleStatus().start(),
                personalSchedule.getPersonalScheduleStatus().done(),
                personalSchedule.getPersonalScheduleTime().startTime(),
                personalSchedule.getPersonalScheduleTime().doneTime(),
                ScheduleRoutineResponse.of(personalSchedule.getScheduleRoutineWindow()));
    }

}