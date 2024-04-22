package com.example.user.dto;

import lombok.Getter;
import lombok.Setter;

public record UserRequest (
         String userEmail,
         String password,
         String userNickname,
         String userType
){
}
