package com.example.rabbitmq;

import com.example.user.external.ExternalUserSender;
import com.example.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ExternalUserHandler implements ExternalUserSender {

    private final ExternalMessageHandler externalMessageHandler;
    @Override
    public void sendUserCachedRefresh(User user) {
        externalMessageHandler.refreshUserCached(UserMessage.of(user));
    }
}
