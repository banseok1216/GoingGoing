package com.example.user.model;

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

    public static User withoutId(String userNickname, String userEmail, UserType userType, Password password, String deviceToken) {
        return new User(null, userNickname, userEmail, userType, password, deviceToken);
    }

    public static User withId(UserId userId, String userNickname, String userEmail, UserType userType, Password password, String deviceToken) {
        return new User(userId, userNickname, userEmail, userType, password, deviceToken);
    }

    public User hashedPassword() {
        return new User(id, userNickname, userEmail, userType, password.hashPassword(), deviceToken);
    }

    public User update(User updateUser) {
        return new User(id, updateUser.getUserNickname(), updateUser.getUserEmail(), updateUser.getUserType(), updateUser.getPassword(), updateUser.getDeviceToken());
    }

    public record UserId(Long value) {
    }

    public record Password(@NonNull String password) {
        public Password hashPassword() {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(password.getBytes());
                return new Password(Base64.getEncoder().encodeToString(hash));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error hashing password", e);
            }
        }

        public boolean matches(String hashedPassword) {
            return hashPassword().password.equals(hashedPassword);
        }
    }

    public enum UserType {
        LOCAL,
        OAUTH_GOOGLE,
        OAUTH_KAKAO
    }
}
