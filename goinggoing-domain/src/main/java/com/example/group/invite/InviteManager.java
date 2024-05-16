package com.example.group.invite;

import com.example.group.model.Group;
import com.example.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InviteManager {
    private final ExternalInviteSender externalInviteSender;
    public void sendInvite(User user, Group.GroupId groupId){
        externalInviteSender.sendInvite(user,groupId);
    }
}
