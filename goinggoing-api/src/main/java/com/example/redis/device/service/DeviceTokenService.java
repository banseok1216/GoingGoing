package com.example.redis.device.service;


import com.example.redis.device.DeviceToken;
import com.example.redis.device.repository.DeviceTokenRepository;
import com.example.user.User;
import org.springframework.stereotype.Service;

@Service
public class DeviceTokenService {
    private final DeviceTokenRepository deviceTokenRepository;

    public DeviceTokenService(DeviceTokenRepository deviceTokenRepository) {
        this.deviceTokenRepository = deviceTokenRepository;
    }
    public void setDeviceToken(User user) {
        DeviceToken existingToken = deviceTokenRepository.findByDeviceToken(user.getDeviceToken());
        if (existingToken != null) {
            existingToken.updateDeviceToken(user.getId().value().toString());
        } else {
            DeviceToken newToken = DeviceToken.builder()
                    .deviceToken(user.getDeviceToken())
                    .userId(user.getId().value().toString())
                    .build();
            deviceTokenRepository.save(newToken);
        }
    }
    public void removeDeviceToken(User.UserId userId, String deviceToken) {
        DeviceToken existingToken = deviceTokenRepository.findByUserIdAndDeviceToken(userId.value().toString(),deviceToken);
        if (existingToken != null) {
            deviceTokenRepository.delete(existingToken);
        }
    }
}
