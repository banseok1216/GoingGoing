package com.example.goinggoing.redis.pub;

import com.example.goinggoing.redis.message.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, MessageDto> redisTemplate;
    public void publish(MessageDto message){
        redisTemplate.convertAndSend("server-to-server-channel", message);
    }
}
