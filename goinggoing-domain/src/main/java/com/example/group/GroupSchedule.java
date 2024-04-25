package com.example.group;

import com.example.personal.PersonalSchedule;
import com.example.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupSchedule {
    private final GroupScheduleId id;
    private final String name;
    private final String description;
    private final String location;
    private final LocalDateTime date;
    private final List<PersonalSchedule> personalSchedules;

    public record GroupScheduleId(Long value) {
    }

    public static GroupSchedule withoutId(String name,String description, String location,LocalDateTime date,List<PersonalSchedule> personalSchedules) {
        return new GroupSchedule(null,name,description,location,date,personalSchedules);
    }

    public static GroupSchedule withId(GroupScheduleId id,String name,String description, String location,LocalDateTime date,List<PersonalSchedule> personalSchedules) {
        return new GroupSchedule(id,name,description,location,date,personalSchedules);
    }

}
