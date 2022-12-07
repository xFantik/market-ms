package ru.pb.market.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler
    public ResponseEntity<AppError> tokenExpired(ExpiredJwtException e){
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Время авторизации истекло. Пожалуйста авторизуйтесь еще раз"), HttpStatus.UNAUTHORIZED);
    }

}
