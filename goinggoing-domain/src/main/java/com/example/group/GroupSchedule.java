package com.example.group;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupSchedule {
    private final GroupScheduleId id;
    private final String groupScheduleName;
    private final String groupScheduleDescription;
    private final String scheduleLocation;
    private final LocalDateTime scheduleDateTime;

    public record GroupScheduleId(Long value) {
    }

    public static GroupSchedule withoutId(String groupScheduleName,String groupScheduleDescription, String scheduleLocation,LocalDateTime scheduleDateTime) {
        return new GroupSchedule(null,groupScheduleName,groupScheduleDescription,scheduleLocation,scheduleDateTime);
    }

    public static GroupSchedule withId(GroupScheduleId groupScheduleId,String groupScheduleName,String groupScheduleDescription, String scheduleLocation,LocalDateTime scheduleDateTime) {
        return new GroupSchedule(groupScheduleId,groupScheduleName,groupScheduleDescription,scheduleLocation,scheduleDateTime);
    }
}
