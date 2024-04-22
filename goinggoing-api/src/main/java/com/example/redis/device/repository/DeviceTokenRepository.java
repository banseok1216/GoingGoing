package com.example.redis.device.repository;
import com.example.redis.device.DeviceToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTokenRepository extends CrudRepository<DeviceToken, String> {
    DeviceToken findByUserIdAndDeviceToken(String userId,String deviceToken);
    DeviceToken findByDeviceToken(String deviceToken);

    DeviceToken findByUserId(String userId);


}
