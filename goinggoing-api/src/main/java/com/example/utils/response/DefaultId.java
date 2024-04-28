package com.example.utils.response;

public record DefaultId(
        Long id
) {
    public static DefaultId of(Long id){
        return new DefaultId(id);
    }
}
