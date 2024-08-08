package com.agorohov.learnirregverbs_bot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class BotExceptionHandler {

    @ExceptionHandler({
        VerbNotFoundByIdException.class,
    })
    public ResponseEntity<String> handleEmployeeExceptions(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
