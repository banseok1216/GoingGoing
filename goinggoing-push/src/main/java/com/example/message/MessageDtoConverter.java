package com.example.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.Message;

public class MessageDtoConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static MessageDto convertToMessageDto(Message message) throws Exception {
        String jsonMessage = new String(message.getBody());
        return objectMapper.readValue(jsonMessage, MessageDto.class);
    }
}