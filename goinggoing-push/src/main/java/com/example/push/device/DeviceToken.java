package com.example.push.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
@RedisHash("deviceToken")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceToken {
    @Id
    private String id;
    private String userId;
    @Indexed
    private String deviceToken;

    public void updateDeviceToken(String userId){
        this.userId = userId;
    }
}