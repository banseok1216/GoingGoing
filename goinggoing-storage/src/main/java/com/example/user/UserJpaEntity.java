package com.example.user;

import com.example.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Column(name = "User_Role")
    private String userRole;
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
                         String userRole, String password, User.UserType userType, String userDeviceToken) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.password = password;
        this.userType = userType;
        this.userDeviceToken = userDeviceToken;
    }
}
