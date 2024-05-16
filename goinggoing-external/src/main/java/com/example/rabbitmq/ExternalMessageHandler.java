package com.example.rabbitmq;

import com.example.user.model.User;
import com.example.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalMessageHandler {
    @Autowired
    private StreamBridge streamBridge;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    public void refreshUserCached(@NotNull Object message) {
        try {
            byte[] payload = objectMapper.writeValueAsBytes(message);
            Message<byte[]> msg = MessageBuilder
                    .withPayload(payload)

                    .build();
            streamBridge.send("userCached-out-0", msg);
        } catch (JsonProcessingException e) {
            log.error("Error serializing message payload: {}", e.getMessage());
        }
    }
    @Bean
    public Consumer<Message<byte[]>> userCached() {
        return message -> {
            byte[] payload = message.getPayload();
            try {
                UserMessage userMessage = objectMapper.readValue(payload,UserMessage.class);
                userService.refreshCachedUser(new User.UserId(userMessage.userId()));
            } catch (IOException e) {
                log.error("Error deserializing message payload: {}", e.getMessage());
            }
        };
    }
    public void notificationPush(@NotNull Object message) {
        try {
            byte[] payload = objectMapper.writeValueAsBytes(message);
            Message<byte[]> msg = MessageBuilder
                    .withPayload(payload)
                    .build();
            streamBridge.send("push-out-0", msg);
        } catch (JsonProcessingException e) {
            log.error("Error serializing message payload: {}", e.getMessage());
        }
    }

}
