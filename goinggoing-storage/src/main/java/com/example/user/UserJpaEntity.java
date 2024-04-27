package com.example.user;

import com.example.BaseEntity;
import com.example.group.GroupJpaEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user", schema = "goinggoing")
public class UserJpaEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "User_Id")
    private Long userId;
    @Basic
    @Column(name = "User_Nickname")
    private String userNickname;
    @Basic
    @Column(name = "User_Email")
    private String userEmail;
    @Basic
    @Column(name = "User_Password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "User_Type")
    private User.UserType userType;
    @Basic
    @Column(name = "User_DeviceToken")
    private String userDeviceToken;

    @Builder
    public UserJpaEntity(Long userId, String userNickname, String userEmail,
                         String password, User.UserType userType, String userDeviceToken) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.password = password;
        this.userType = userType;
        this.userDeviceToken = userDeviceToken;
    }
    public static UserJpaEntity ofDomain(User user) {
        return UserJpaEntity.builder()
                .userId(user.getId().value())
                .userNickname(user.getUserNickname())
                .userEmail(user.getUserEmail())
                .password(user.getPassword().password())
                .userType(user.getUserType())
                .userDeviceToken(user.getDeviceToken())
                .build();
    }
    public User toUser() {
        return User.withId(new User.UserId(this.userId),this.userNickname,this.userEmail,this.userType,new User.Password(this.password), this.userDeviceToken);
    }
}
