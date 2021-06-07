package com.jwt.controller;

import com.jwt.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorDto("존재하지 않는 아이디이거나 비밀번호가 일치하지 않습니다."), HttpStatus.UNAUTHORIZED);
    }
}
