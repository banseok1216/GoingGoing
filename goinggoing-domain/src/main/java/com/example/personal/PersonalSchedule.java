package com.example.personal;

import com.example.routine.RoutineWindow;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalSchedule {
    private final PersonalScheduleId id;
    private final Integer personalDuration;
    private final PersonalScheduleTime personalScheduleTime;
    private final PersonalScheduleStatus personalScheduleStatus;
    private final RoutineWindow scheduleRoutineWindow;

    public static PersonalSchedule withoutId(
            Integer personalDuration,
            PersonalScheduleTime personalScheduleTime,
            PersonalScheduleStatus personalScheduleStatus,
            RoutineWindow scheduleRoutineWindow) {
        return new PersonalSchedule(null, personalDuration, personalScheduleTime,personalScheduleStatus,scheduleRoutineWindow);
    }

    public static PersonalSchedule withId(
            PersonalScheduleId personalScheduleId,
            Integer personalDuration,
            PersonalScheduleTime personalScheduleTime,
            PersonalScheduleStatus personalScheduleStatus,
            RoutineWindow scheduleRoutineWindow) {
        return new PersonalSchedule(personalScheduleId, personalDuration, personalScheduleTime,personalScheduleStatus,scheduleRoutineWindow);
    }
    public PersonalSchedule updateStatusAndTime() {
        PersonalScheduleTime newPersonalScheduleTime = this.personalScheduleTime.calculateStartTime(this.personalScheduleTime,this.scheduleRoutineWindow.calculateTotalTime(),this.personalDuration);
        PersonalScheduleStatus newPersonalScheduleStatus = newPersonalScheduleTime.checkAndUpdateStatus();
        return new PersonalSchedule(this.id, this.personalDuration,newPersonalScheduleTime,newPersonalScheduleStatus,this.scheduleRoutineWindow);
    }

    public record PersonalScheduleId(Long value) {
    }


    public record PersonalScheduleTime(LocalDateTime startTime, LocalDateTime doneTime) {
        public PersonalScheduleTime calculateStartTime(PersonalScheduleTime personalScheduleTime, Long totalTime, Integer duration) {
            return new PersonalScheduleTime(personalScheduleTime.doneTime, this.doneTime.minusSeconds(totalTime).minusMinutes(duration));
        }

        public PersonalScheduleTime calculateDoneTime(PersonalScheduleTime personalScheduleTime, Long totalTime) {
            return new PersonalScheduleTime(personalScheduleTime.startTime, this.doneTime.minusSeconds(totalTime));
        }

        public PersonalScheduleStatus checkAndUpdateStatus() {
            LocalDateTime currentTime = LocalDateTime.now();
            if (this.doneTime.isBefore(currentTime)) {
                return new PersonalScheduleStatus(true, true);
            } else if (this.startTime.isAfter(currentTime)) {
                return new PersonalScheduleStatus(false, false);
            } else {
                return new PersonalScheduleStatus(true, false);
            }
        }
    }

    public record PersonalScheduleStatus(Boolean start, Boolean done) {
    }
}
