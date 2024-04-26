package com.example.group.dto;
import com.example.user.User;

import java.util.List;
import java.util.stream.Collectors;

public record GroupMemberResponse(
    Long userId,
    String userNickname
) {
    public static List<GroupMemberResponse> of(List<User> users){
        return users.stream()
                .map(user -> new GroupMemberResponse(user.getId().value(), user.getUserNickname()))
                .collect(Collectors.toList());
    }
}