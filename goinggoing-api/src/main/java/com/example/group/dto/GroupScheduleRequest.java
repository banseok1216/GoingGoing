package com.example.group.dto;


import com.example.group.GroupSchedule;
import com.example.personal.PersonalSchedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record GroupScheduleRequest(
        Long groupId,
        Long groupScheduleId,
        String groupScheduleName,
        LocalDateTime groupScheduleDateTime,
        String groupScheduleLocation,
        String groupDescription
) {
    public GroupSchedule toCreateGroupSchedule() {
        return GroupSchedule.withoutId(this.groupScheduleName,
                this.groupDescription, this.groupScheduleLocation,
                this.groupScheduleDateTime, List.of(PersonalSchedule.initialized()));
    }
    public GroupSchedule toModifyGroupSchedule() {
        return GroupSchedule.withId(new GroupSchedule.GroupScheduleId(groupScheduleId) ,this.groupScheduleName,
                this.groupDescription, this.groupScheduleLocation,
                this.groupScheduleDateTime,List.of(PersonalSchedule.initialized()));
    }
}

