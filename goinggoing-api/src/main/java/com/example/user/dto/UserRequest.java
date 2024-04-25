package com.example.user.dto;

import com.example.user.User;
import lombok.Getter;
import lombok.Setter;

public record UserRequest (
         String userEmail,
         String password,
         String userNickname,
         String userType
){
    public User toKakaoLoginUser(String deviceToken){
        return User.withoutId(this.userNickname, this.userEmail, User.UserType.OAUTH_KAKAO,null,deviceToken);
    }

    public User toGoogleLoginUser(String deviceToken){
        return User.withoutId(this.userNickname, this.userEmail, User.UserType.OAUTH_GOOGLE,null,deviceToken);
    }

    public User toDefaultLoginUser(String deviceToken){
        return User.withoutId(this.userNickname, this.userEmail, User.UserType.OAUTH_DEFAULT,new User.Password(this.password),deviceToken);
    }

    public User toDefaultRegisterUser() {
        return User.withoutId(this.userNickname, this.userEmail, User.UserType.OAUTH_DEFAULT, new User.Password(this.password),null);
    }
}
