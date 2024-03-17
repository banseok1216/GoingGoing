package com.example.push.device.repository;
import com.example.push.device.DeviceToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTokenRepository extends CrudRepository<DeviceToken, String> {

    DeviceToken findByUserId(String userId);

}
