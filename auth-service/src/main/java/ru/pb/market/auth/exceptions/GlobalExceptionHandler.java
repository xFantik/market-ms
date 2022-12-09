package ru.pb.market.auth.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<AppError> userNameIsBusy(RuntimeException e){
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> tokenExpired(ExpiredJwtException e){
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Время авторизации истекло. Пожалуйста авторизуйтесь еще раз"), HttpStatus.UNAUTHORIZED);
    }

}
