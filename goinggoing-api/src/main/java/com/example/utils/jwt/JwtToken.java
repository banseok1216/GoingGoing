package com.example.utils.jwt;

public record JwtToken(
        String AccessToken,
        String RefreshToken

) {
    public JwtToken toToken(){
        return new JwtToken(AccessToken,RefreshToken);
    }
}
