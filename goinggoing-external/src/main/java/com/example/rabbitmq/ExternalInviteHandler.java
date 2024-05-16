package com.example.rabbitmq;

import com.example.group.invite.ExternalInviteSender;
import com.example.group.model.Group;
import com.example.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExternalInviteHandler implements ExternalInviteSender {

    private final ExternalMessageHandler externalMessageHandler;
    @Override
    public void sendInvite(User user, Group.GroupId groupId) {
        externalMessageHandler.notificationPush(InviteMessage.of(user, groupId));
    }
}

