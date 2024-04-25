package com.example.utils.response;

public record DefaultId(
        Long Id
) {
    public static DefaultId of(Long id){
        return new DefaultId(id);
    }
}
