package com.example.group.model;

import com.example.personal.model.PersonalSchedule;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Group {
    private final GroupId id;
    private final GroupSchedule groupSchedule;
    private final List<PersonalSchedule> personalSchedules;

    public static Group withoutId(
            GroupSchedule groupSchedule, List<PersonalSchedule> personalSchedules) {
        return new Group(null,groupSchedule,personalSchedules);
    }

    public static Group withId(
            GroupId groupId,
            GroupSchedule groupSchedule,
            List<PersonalSchedule> personalSchedules
    ) {
        return new Group(groupId, groupSchedule,personalSchedules);
    }


    public record GroupId(Long value) {}
}
