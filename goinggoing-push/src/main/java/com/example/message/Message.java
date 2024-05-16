package com.example.message;

import lombok.Data;

@Data
public class Message {
    private String deviceToken;
    private Long scheduleId;
    private String content;
}
