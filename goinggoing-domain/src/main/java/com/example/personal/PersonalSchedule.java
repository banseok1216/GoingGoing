package com.example.personal;

import com.example.group.GroupSchedule;
import com.example.routine.RoutineWindow;
import com.example.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalSchedule {
    private final PersonalScheduleId id;
    private final Integer personalDuration;
    private final PersonalScheduleTime personalScheduleTime;
    private final PersonalScheduleStatus personalScheduleStatus;
    private final RoutineWindow scheduleRoutineWindow;
    private User user;
    private final PersonalScheduleSend personalScheduleSend;


    public static PersonalSchedule withId(PersonalScheduleId personalScheduleId, Integer personalDuration, PersonalScheduleTime personalScheduleTime, PersonalScheduleStatus personalScheduleStatus, RoutineWindow scheduleRoutineWindow, User user, PersonalScheduleSend personalScheduleSend) {
        return new PersonalSchedule(personalScheduleId, personalDuration, personalScheduleTime, personalScheduleStatus, scheduleRoutineWindow, user, personalScheduleSend);
    }

    public static PersonalSchedule initialized(User user, GroupSchedule groupSchedule) {
        return new PersonalSchedule(null, 0, new PersonalScheduleTime(groupSchedule.getDate(), groupSchedule.getDate()), new PersonalScheduleStatus(false, false), null, user, new PersonalScheduleSend(false, false));
    }

    public PersonalSchedule updateStatusAndTime() {
        PersonalScheduleTime newPersonalScheduleTime = this.personalScheduleTime.calculateTime(this.scheduleRoutineWindow.calculateTotalTime(), this.personalDuration);
        PersonalScheduleStatus newPersonalScheduleStatus = newPersonalScheduleTime.checkAndUpdateStatus();
        return new PersonalSchedule(this.id, this.personalDuration, newPersonalScheduleTime, newPersonalScheduleStatus, this.scheduleRoutineWindow, this.user, this.personalScheduleSend);
    }

    public record PersonalScheduleId(Long value) {
    }

    public record PersonalScheduleSend(Boolean sendStartMessage, Boolean sendEndMessage) {
    }


    public record PersonalScheduleTime(LocalDateTime startTime, LocalDateTime doneTime) {
        public PersonalScheduleTime calculateTime(Long totalTime, Integer duration) {
            return new PersonalScheduleTime(this.doneTime.minusSeconds(totalTime).minusMinutes(duration), this.doneTime.minusSeconds(totalTime));
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
