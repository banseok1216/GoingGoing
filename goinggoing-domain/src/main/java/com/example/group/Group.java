package com.example.group;

import com.example.personal.PersonalSchedule;
import com.example.user.User;
import lombok.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Group {
    @Getter
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


    @Value
    public static class GroupId {
        private Long value;
    }
}
