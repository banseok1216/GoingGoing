package com.example.goinggoing.group.dto;

import lombok.Getter;
import lombok.Setter;

public class GroupMemberResponseDto {
    @Getter
    @Setter
    public static class GetGroup {
        private Long userId;
        private String userNickname;
    }
    @Getter
    @Setter
    public static class AddGroup {
        private Long personalScheduleId;
    }
}