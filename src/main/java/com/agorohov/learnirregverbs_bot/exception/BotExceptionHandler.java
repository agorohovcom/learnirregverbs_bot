package com.agorohov.learnirregverbs_bot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
@Slf4j
public class BotExceptionHandler {

    @ExceptionHandler({
            VerbNotFoundByIdException.class,
            VerbFormNotFoundByIndexException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
        log.error("Ошибка: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
