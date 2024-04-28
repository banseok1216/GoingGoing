//package com.example.goinggoing.device;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import com.example.redis.device.DeviceToken;
//import com.example.redis.device.service.DeviceTokenService;
//import com.example.redis.device.repository.DeviceTokenRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import static org.mockito.Mockito.when;
//
//public class DeviceTokenServiceUnitTest {
//    @Mock
//    private DeviceTokenRepository deviceTokenRepository;
//
//    @InjectMocks
//    private DeviceTokenService deviceTokenService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Captor
//    ArgumentCaptor<DeviceToken> deviceTokenCaptor = ArgumentCaptor.forClass(DeviceToken.class);
//
//
//    @Test
//    @DisplayName("존재하는 토큰 수정")
//    void testSetDeviceToken_existingToken() {
//        String userId = "1";
//        String deviceToken = "testToken";
//        DeviceToken existingToken = DeviceToken.builder().userId(userId).deviceToken(deviceToken).build();
//        when(deviceTokenRepository.findByDeviceToken(deviceToken)).thenReturn(existingToken);
//        deviceTokenService.setDeviceToken(userId, deviceToken);
//        verify(deviceTokenRepository, never()).save(any(DeviceToken.class));
//    }
//    @Test
//    @DisplayName("존재하지 않는 토큰 추가")
//    void testSetDeviceToken_notExistingToken() {
//        String userId = "1";
//        String deviceToken = "testToken";
//        when(deviceTokenRepository.findByDeviceToken(deviceToken)).thenReturn(null);
//        deviceTokenService.setDeviceToken(userId, deviceToken);
//        verify(deviceTokenRepository, times(1)).save(deviceTokenCaptor.capture());
//        DeviceToken savedDeviceToken = deviceTokenCaptor.getValue();
//        assertEquals(savedDeviceToken.getDeviceToken(), deviceToken);
//        assertEquals(savedDeviceToken.getUserId(), userId);
//    }
//
//    @Test
//    @DisplayName("토큰 삭제")
//    void testSetDeviceToken_removeToken() {
//        String userId = "1";
//        String deviceToken = "testToken";
//        DeviceToken findDeviceToken = DeviceToken.builder().userId(userId).deviceToken(deviceToken).build();
//        when(deviceTokenRepository.findByUserIdAndDeviceToken(userId,deviceToken)).thenReturn(findDeviceToken);
//        deviceTokenService.removeDeviceToken(userId, deviceToken);
//        verify(deviceTokenRepository,times(1)).delete(findDeviceToken);
//    }
//}
