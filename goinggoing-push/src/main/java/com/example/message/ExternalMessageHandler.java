package com.example.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
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
    private final FirebaseCloudMessageService firebaseCloudMessageService;
    @Bean
    public Consumer<org.springframework.messaging.Message<byte[]>> push() {
        return message -> {
            byte[] payload = message.getPayload();
            try {
                Message messageDto = objectMapper.readValue(payload, Message.class);
                firebaseCloudMessageService.sendMessageTo(messageDto);
            } catch (IOException e) {
                log.error("Error deserializing message payload: {}", e.getMessage());
            }
        };
    }
}
