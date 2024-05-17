package com.example.user.dto;
import com.example.user.model.User;
public record UserRequest(
        defaultLogin loginRequest,
        oauthLogin oauthLoginRequest,
        register registerRequest
    ) {
    public record defaultLogin(
            String userEmail,
            String password
    ) {
        public User toDefaultLoginUser(String deviceToken) {
            return User.withoutId(null, userEmail, User.UserType.LOCAL, new User.Password(password), deviceToken);
        }
    }
    public record oauthLogin(
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
    public record register(
            String userEmail,
            String password,
            String userNickname
    ){
        public User toDefaultRegisterUser() {
            return User.withoutId(userNickname, userEmail, User.UserType.LOCAL, new User.Password(password),null);
        }
    }
}
