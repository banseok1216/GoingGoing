package com.example.goinggoing.redis.device.service;


import com.example.goinggoing.redis.device.DeviceToken;
import com.example.goinggoing.redis.device.repository.DeviceTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceTokenService {
    private final DeviceTokenRepository deviceTokenRepository;

    public DeviceTokenService(DeviceTokenRepository deviceTokenRepository) {
        this.deviceTokenRepository = deviceTokenRepository;
    }
    public void setDeviceToken(String userId,String deviceToken) {
        DeviceToken existingToken = deviceTokenRepository.findByDeviceToken(deviceToken);
        if (existingToken != null) {
            existingToken.updateDeviceToken(userId);
        } else {
            DeviceToken newToken = DeviceToken.builder()
                    .deviceToken(deviceToken)
                    .userId(userId)
                    .build();
            deviceTokenRepository.save(newToken);
        }
    }
    public void removeDeviceToken(String userId,String deviceToken) {
        DeviceToken existingToken = deviceTokenRepository.findByUserIdAndDeviceToken(userId,deviceToken);
        if (existingToken != null) {
            deviceTokenRepository.delete(existingToken);
        }
    }
}
