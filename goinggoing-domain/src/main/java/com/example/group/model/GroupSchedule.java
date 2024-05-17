package com.example.group.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupSchedule {
    private final GroupScheduleId id;
    private final String name;
    private final String description;
    private final String location;
    private final LocalDateTime date;
    public record GroupScheduleId(Long value) {
    }

    public static GroupSchedule withoutId(String name,String description, String location,LocalDateTime date) {
        return new GroupSchedule(null,name,description,location,date);
    }

    public static GroupSchedule withId(GroupScheduleId id,String name,String description, String location,LocalDateTime date) {
        return new GroupSchedule(id,name,description,location,date);
    }
    public GroupSchedule update(GroupSchedule groupSchedule) {
        return new GroupSchedule(this.id,groupSchedule.name,groupSchedule.description,groupSchedule.location,groupSchedule.date);
    }

}
