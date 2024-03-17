package com.example.goinggoing.user.mapstruct;

import com.example.goinggoing.user.dto.UserRequestDto;
import com.example.goinggoing.user.dto.UserResponseDto;
import com.example.goinggoingdomain.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponseDto toUserResponseDto(User user);
    User toEntity(UserRequestDto userRequestDto);

    default User toKakaoUser(User userRegister){
        return User.builder()
                .userType("kakao")
                .password(null)
                .userNickname(userRegister.getUserNickname())
                .userEmail(userRegister.getUserEmail())
                .userRole("user")
                .build();
    }
    default User toDefaultUser(User userRegister, String hashedPassword){
        return User.builder()
                .userType("goinggoing")
                .password(hashedPassword)
                .userNickname(userRegister.getUserNickname())
                .userEmail(userRegister.getUserEmail())
                .userRole("user")
                .build();
    }

}
