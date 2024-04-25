package com.example.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INTERNAL_SERVER_ERROR("COMMON_001", "Internal Server Error"),
    // User
    USER_NOT_FOUND("USER_001", "회원을 찾을 수 없습니다."),
    USER_IS_DUPLICATE("USER_002", "회원이 중복되었습니다."),
    USER_LOGIN_PASSWORD_FAIL("USER_003", "로그인 비밀번호가 잘못 되었습니다."),
    USER_ROUTINE_NOT_FOUND("USER_004", "회원을 찾을 수 없습니다."),
    GROUP_NOT_FOUND("GROUP_011", "그룹을 찾을 수 없습니다.");



    private static final Map<String, ErrorCode> ERROR_CODE_MAP=  Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(ErrorCode::getMessage, Function.identity())));
    private final String code;
    private final String message;

    public static ErrorCode from(final String message) {
        if (ERROR_CODE_MAP.containsKey(message)) {
            return ERROR_CODE_MAP.get(message);
        }

        return ErrorCode.INTERNAL_SERVER_ERROR;
    }
}
