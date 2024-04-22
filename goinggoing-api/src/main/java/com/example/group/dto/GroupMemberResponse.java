package com.example.group.dto;
import java.util.List;

public record GroupMemberResponse(
        List<GetGroup> getGroupList,
        AddGroup addGroup
) {
    public record GetGroup(
            Long userId,
            String userNickname
    ) {
    }
    public record AddGroup(
            Long personalScheduleId
    ) {
    }
}