package com.example.redis.message;

import com.example.group.model.Group;
import com.example.user.domain.User;

public record Message(
        String deviceToken,
        Long groupId,
        String content
) {
    public static Message of(User user, Group.GroupId groupId){
        return new Message(user.getDeviceToken(),groupId.value(),user.getUserNickname() + "님이 일정에 초대했어요");
    }
}
