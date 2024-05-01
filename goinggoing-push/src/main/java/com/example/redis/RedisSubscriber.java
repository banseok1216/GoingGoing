package com.example.redis;

import com.example.scheduling.SchedulingService;
import com.example.group.model.Group;
import com.example.group.service.GroupMemberService;
import com.example.message.FirebaseCloudMessageService;
import com.example.message.MessageDto;
import com.example.message.MessageDtoConverter;
import com.example.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class RedisSubscriber implements MessageListener {
    private final FirebaseCloudMessageService firebaseCloudMessageService;

    public RedisSubscriber(FirebaseCloudMessageService firebaseCloudMessageService, SchedulingService schedulingService) {
        this.firebaseCloudMessageService = firebaseCloudMessageService;
    }
    @Override
    public void onMessage(@NotNull Message message, byte[] pattern) {
        try {
            MessageDto messageDto = MessageDtoConverter.convertToMessageDto(message);
            firebaseCloudMessageService.sendMessageTo(messageDto.getDeviceToken(), "알림이 도착했어요", messageDto.getContent());
        } catch (Exception e) {
            log.error("redis 메시지 구동중 오류발생");
        }
    }
}
