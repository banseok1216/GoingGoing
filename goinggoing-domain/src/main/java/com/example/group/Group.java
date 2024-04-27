package com.example.group;

import com.example.personal.PersonalSchedule;
import com.example.user.User;
import lombok.*;

import java.util.ArrayList;
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
