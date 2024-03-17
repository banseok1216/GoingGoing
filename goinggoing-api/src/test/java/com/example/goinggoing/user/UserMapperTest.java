package com.example.goinggoing.user;
import com.example.goinggoing.user.dto.UserRequestDto;
import com.example.goinggoing.user.dto.UserResponseDto;
import com.example.goinggoing.user.mapstruct.UserMapper;
import com.example.goinggoingdomain.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    @DisplayName("UserRequestDto를 user 엔티티로 매핑하는지 확인")
    public void testToEntityMapping() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUserEmail("testEmail");
        requestDto.setPassword("testPassword");
        requestDto.setUserNickname("testUserName");
        requestDto.setUserType("testUser");
        User userEntity = userMapper.toEntity(requestDto);
        assertEquals(requestDto.getUserEmail(), userEntity.getUserEmail());
        assertEquals(requestDto.getUserNickname(), userEntity.getUserNickname());
        assertEquals(requestDto.getPassword(), userEntity.getPassword());
        assertEquals(requestDto.getUserType(), userEntity.getUserType());
    }

    @Test
    @DisplayName("user 엔티티를 UserResponseDto로 매핑하는지 확인")
    public void testToUserResponseDtoMapping() {
        User userEntity = User.builder().userId(1L).userEmail("userEmail").userNickname("userNickName").build();
        UserResponseDto responseDto = userMapper.toUserResponseDto(userEntity);
        assertEquals(userEntity.getUserEmail(), responseDto.getUserEmail());
        assertEquals(userEntity.getUserNickname(), responseDto.getUserNickname());
    }

    @Test
    @DisplayName("기본 유저로 매핑하는지 확인")
    public void testToDefaultUserMapping() {
        String testEmail = "testEmail";
        String testNickName = "testNickName";
        User fakeUser = User.builder().userEmail(testEmail).userNickname(testNickName).build();
        String hashedPassword = "hashedPassword";
        User defaultUser = userMapper.toDefaultUser(fakeUser,hashedPassword);
        assertEquals("goinggoing", defaultUser.getUserType());
        assertEquals(hashedPassword, defaultUser.getPassword());
        assertEquals(testEmail, defaultUser.getUserEmail());
        assertEquals(testNickName, defaultUser.getUserNickname());
        assertEquals("user", defaultUser.getUserRole());
    }

    @Test
    @DisplayName("카카오 유저로 매핑하는지 확인")
    public void testToKakaoUserMapping() {
        String testEmail = "testEmail";
        String testNickName = "testNickName";
        User fakeUser = User.builder().userEmail(testEmail).userNickname(testNickName).build();
        User kakaoUser = userMapper.toKakaoUser(fakeUser);
        assertEquals("kakao", kakaoUser.getUserType());
        assertEquals(null, kakaoUser.getPassword());
        assertEquals(testEmail, kakaoUser.getUserEmail());
        assertEquals(testNickName, kakaoUser.getUserNickname());
        assertEquals("user", kakaoUser.getUserRole());
    }
}
