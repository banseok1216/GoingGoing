package com.example.user;

import lombok.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final UserId id;

    private final String userNickname;

    private final String userEmail;

    private final UserType userType;

    private final Password password;

    private final String deviceToken;

    public static User withoutId(String userNickname, String userEmail, UserType userType, Password password,String deviceToken) {
        return new User(null, userNickname, userEmail, userType, password,deviceToken);
    }

    public static User withId(UserId userId, String userNickname, String userEmail, UserType userType, Password password,String deviceToken) {
        return new User(userId, userNickname, userEmail, userType, password, deviceToken);
    }

    public record UserId(Long value) {}

    public record Password(String password) {
        public String hashPassword() {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes());
                return Base64.getEncoder().encodeToString(hash);
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
        }

        public boolean matches(String hashedPassword) {
            return hashPassword().equals(hashedPassword);
        }
    }

    public enum UserType {
        OAUTH_DEFAULT,
        OAUTH_GOOGLE,
        OAUTH_KAKAO
    }
}
