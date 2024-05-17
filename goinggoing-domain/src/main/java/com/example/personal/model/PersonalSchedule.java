package com.example.personal.model;

import com.example.group.model.GroupSchedule;
import com.example.routine.model.RoutineWindow;
import com.example.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalSchedule {
    private final PersonalScheduleId id;
    private final Integer duration;
    private final Time scheduleTime;
    private final Status status;
    private final RoutineWindow routineWindow;
    private User user;
    private final Send send;

    public static PersonalSchedule withId(PersonalScheduleId id, int duration, Time scheduleTime, Status status, RoutineWindow routineWindow, User user, Send send) {
        return new PersonalSchedule(id, duration, scheduleTime, status, routineWindow, user, send);
    }

    public static PersonalSchedule initialized(User user, GroupSchedule groupSchedule) {
        LocalDateTime dateTime = groupSchedule.getDate();
        Time scheduleTime = new Time(dateTime, dateTime);
        return new PersonalSchedule(null, 0, scheduleTime, Status.initial(), null, user, Send.initial());
    }

    public PersonalSchedule updateStatusAndTime() {
        Time newScheduleTime = scheduleTime.calculateTime(routineWindow.calculateTotalTime(), duration);
        Status newStatus = newScheduleTime.checkAndUpdateStatus();
        return withId(id, duration, newScheduleTime, newStatus, routineWindow, user, send);
    }
    public PersonalSchedule updateStartNotified() {
        return withId(id, duration, scheduleTime, status, routineWindow, user, send.updatedSendStart());
    }
    public PersonalSchedule updateEndNotified() {
        return withId(id, duration, scheduleTime, status, routineWindow, user, send.updatedSendEnd());
    }
    public PersonalSchedule updatePersonalSchedule(PersonalSchedule updated) {
        return withId(id, updated.duration, updated.scheduleTime, updated.status, updated.routineWindow,updated.user, updated.send);
    }
    public record PersonalScheduleId(Long value) {}
    public record Send(Boolean sendStartMessage, Boolean sendEndMessage) {
        public static Send initial() {
            return new Send(false,false);
        }
        public Send updatedSendStart() {
            return new Send(true, this.sendEndMessage);
        }
        public Send updatedSendEnd() {
            return new Send(this.sendStartMessage, true);
        }
    }
    public record Time(LocalDateTime startTime, LocalDateTime doneTime) {
        public Time calculateTime(long totalTime, int duration) {
            LocalDateTime newStartTime = doneTime.minusSeconds(totalTime).minusMinutes(duration);
            return new Time(newStartTime, doneTime.minusMinutes(duration));
        }
        public Status checkAndUpdateStatus() {
            LocalDateTime currentTime = LocalDateTime.now();
            if (doneTime.isBefore(currentTime)) {
                return new Status(true, true);
            } else if (startTime.isAfter(currentTime)) {
                return new Status(false, false);
            } else {
                return new Status(true, false);
            }
        }
    }
    public record Status(Boolean start, Boolean done) {
        public static Status initial(){
            return new Status(false,false);
        };
    }
}
