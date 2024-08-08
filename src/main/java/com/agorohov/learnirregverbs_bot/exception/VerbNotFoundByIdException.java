package com.agorohov.learnirregverbs_bot.exception;

public class VerbNotFoundByIdException extends RuntimeException{
    public VerbNotFoundByIdException(String message){
        super(message);
    }
}
