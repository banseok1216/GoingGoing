package com.example.group.dto;


import com.example.group.model.GroupSchedule;

import java.time.LocalDateTime;

public record GroupScheduleRequest(
        Create create,
        Update update
        ){
    public record Create(
            String groupScheduleName,
            LocalDateTime groupScheduleDateTime,
            String groupScheduleLocation,
            String groupDescription
    ) {
        public GroupSchedule toCreateGroupSchedule() {
            return GroupSchedule.withoutId(this.groupScheduleName,
                    this.groupDescription, this.groupScheduleLocation,
                    this.groupScheduleDateTime);
        }
    }
    public record Update(
            Long groupScheduleId,
            String groupScheduleName,
            LocalDateTime groupScheduleDateTime,
            String groupScheduleLocation,
            String groupDescription
    ) {
        public GroupSchedule toModifyGroupSchedule() {
            return GroupSchedule.withId(new GroupSchedule.GroupScheduleId(groupScheduleId) ,this.groupScheduleName,
                    this.groupDescription, this.groupScheduleLocation,
                    this.groupScheduleDateTime);
        }
    }
}

