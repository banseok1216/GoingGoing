package com.example.personal.dto;

import com.example.personal.PersonalSchedule;
import com.example.routine.RoutineWindow;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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