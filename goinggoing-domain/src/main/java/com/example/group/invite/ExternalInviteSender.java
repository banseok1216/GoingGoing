package com.example.group.invite;

import com.example.group.model.Group;
import com.example.user.model.User;

public interface ExternalInviteSender {
    void sendInvite(User user, Group.GroupId groupId);
}
