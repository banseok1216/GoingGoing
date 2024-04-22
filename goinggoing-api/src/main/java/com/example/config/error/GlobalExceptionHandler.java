package com.example.config.error;

import com.example.error.BusinessException;
import com.example.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("Message : "+ e.getMessage());
        final ErrorCode errorCode = ErrorCode.from(e.getMessage());
        final ErrorResponse response = ErrorResponse.from(errorCode);

        if (errorCode == ErrorCode.INTERNAL_SERVER_ERROR) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("ErrorCode : "+ e.getErrorCode().getCode());
        log.error("Message : "+ e.getErrorCode().getMessage());
        final ErrorResponse response = ErrorResponse.from(e.getErrorCode());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
