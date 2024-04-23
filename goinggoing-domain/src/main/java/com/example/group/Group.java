package com.example.group;

import com.example.user.User;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Group {
    private final GroupId id;
    private final User.UserId userId;
    private final GroupSchedule groupSchedule;

    public static Group withoutId(
            User.UserId userId,
            GroupSchedule groupSchedule) {
        return new Group(null, userId, groupSchedule);
    }

    public static Group withId(
            GroupId groupId,
            User.UserId userId,
            GroupSchedule groupSchedule) {
        return new Group(groupId, userId, groupSchedule);
    }

    public record GroupId(Long value) {}
}
