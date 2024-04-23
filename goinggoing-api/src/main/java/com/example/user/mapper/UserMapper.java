package com.example.user.mapper;


import com.example.user.User;
import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse mapToUserResponseDto(User user){
        return new UserResponse(user.getId().getValue(), user.getUserNickname(), user.getUserEmail());
    };

    public User mapToKakaoLoginUser(UserRequest userRequest){
        return User.withoutId(userRequest.userNickname(), userRequest.userEmail(), User.UserType.OAUTH_KAKAO,null);
    }

    public User mapToGoogleLoginUser(UserRequest userRequest){
        return User.withoutId(userRequest.userNickname(), userRequest.userEmail(), User.UserType.OAUTH_GOOGLE,null);
    }

    public User mapToDefaultLoginUser(UserRequest userRequest){
        return User.withoutId(userRequest.userNickname(), userRequest.userEmail(), User.UserType.OAUTH_DEFAULT,new User.Password(userRequest.password()));
    }

    public User mapToDefaultRegisterUser(UserRequest userRequest) {
        return User.withoutId(userRequest.userNickname(), userRequest.userEmail(), User.UserType.OAUTH_DEFAULT, new User.Password(userRequest.password()));
    }

    public User.UserId mapToUserId(Long userId) {
        return new User.UserId(userId);
    }

    public
}
