package com.example.goinggoing.user.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserRequestDto {
    private String userEmail;
    private String password;
    private String userNickname;
    private String userType;
}
