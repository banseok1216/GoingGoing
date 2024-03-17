package com.example.goinggoing.redis.device;

import lombok.*;
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