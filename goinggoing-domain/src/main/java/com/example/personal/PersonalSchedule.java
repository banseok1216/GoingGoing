package com.example.personal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalSchedule {
    private final PersonalScheduleId personalScheduleId;
    @Value
    public static class PersonalScheduleId {
        Long value;
    }
}
