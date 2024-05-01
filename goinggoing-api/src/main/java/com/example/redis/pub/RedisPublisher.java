package com.example.redis.pub;

import com.example.redis.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Message> redisTemplate;
    public void publish(Message message){
        redisTemplate.convertAndSend("server-to-server-channel", message);
    }
}
