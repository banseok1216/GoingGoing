package com.example.push.device.service;

import com.example.push.device.DeviceToken;
import com.example.push.device.repository.DeviceTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceTokenService {
    private final DeviceTokenRepository deviceTokenRepository;

    public DeviceTokenService(DeviceTokenRepository deviceTokenRepository) {
        this.deviceTokenRepository = deviceTokenRepository;
    }
    public DeviceToken getDeviceToken(String userId) {
        return deviceTokenRepository.findByUserId(userId);
    }
}
