package com.example.user.external;

import com.example.user.model.User;

public interface ExternalUserSender{
    void sendUserCachedRefresh(User user);
}
