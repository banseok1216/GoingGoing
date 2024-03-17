package com.example.goinggoing.redis.message;

import lombok.Data;

@Data
public class MessageDto {
    private String deviceToken;
    private Long scheduleId;
    private String content;
}
