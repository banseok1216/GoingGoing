package com.example.redis.pub;

import com.example.redis.message.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, MessageDto> redisTemplate;
    public void publish(MessageDto message){
        redisTemplate.convertAndSend("server-to-server-channel", message);
    }
}
