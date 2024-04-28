package com.example.user.dto;
import com.example.user.User;
public record UserRequest(
        UserLogin loginRequest,
        UserOauthLogin oauthLoginRequest,
        UserRegister registerRequest
    ) {
    public record UserLogin(
            String userEmail,
            String password
    ) {
        public User toDefaultLoginUser(String deviceToken) {
            return User.withoutId(null, userEmail, User.UserType.OAUTH_DEFAULT, new User.Password(password), deviceToken);
        }
    }
    public record UserOauthLogin(
            String userEmail,
            String userNickname
    ) {
        public User toKakaoLoginUser(String deviceToken) {
            return User.withoutId(userNickname, userEmail, User.UserType.OAUTH_KAKAO, null, deviceToken);
        }
        public User toGoogleLoginUser(String deviceToken) {
            return User.withoutId(userNickname, userEmail, User.UserType.OAUTH_GOOGLE, null, deviceToken);
        }
    }
    public record UserRegister(
            String userEmail,
            String password,
            String userNickname
    ){
        public User toDefaultRegisterUser() {
            return User.withoutId(userNickname, userEmail, User.UserType.OAUTH_DEFAULT, new User.Password(password),null);
        }
    }
}
