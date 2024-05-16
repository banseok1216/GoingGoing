package com.example.rabbitmq;

import com.example.group.model.Group;
import com.example.user.model.User;

public record InviteMessage(
        String deviceToken,
        Long groupId,
        String content
) {
    public static InviteMessage of(User user, Group.GroupId groupId) {
        return new InviteMessage(user.getDeviceToken(), groupId.value(), user.getUserNickname() + "님이 일정에 초대했어요");
    }
}