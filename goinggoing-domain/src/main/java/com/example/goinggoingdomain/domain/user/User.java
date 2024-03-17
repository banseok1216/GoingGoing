package com.example.goinggoingdomain.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user", schema = "goinggoing")
public class User {
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
    @Basic
    @Column(name = "User_Type")
    private String userType;

    @Builder
    public User(Long userId, String userNickname, String userEmail,
                String userRole, String password, String userType) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return userId == that.userId &&
                Objects.equals(userNickname, that.userNickname) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userRole, that.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userNickname, userEmail, userRole);
    }

}
