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
    private final List<User> users;

    public static Group withoutId(
            List<User> users) {
        return new Group(null, users);
    }

    public static Group withId(
            GroupId groupId,
            List<User> users
    ) {
        return new Group(groupId, users);
    }

    public record GroupId(Long value) {}

    public static List<User> createGroupWithSingeUser(User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }
}
