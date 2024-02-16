package com.agorohov.learnirregverbs_bot.component.update_handler;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public abstract class UpdateHandler {
    
    protected UpdateProcessingStrategy processingStrategy;
    
    protected Update update;
    protected boolean isAdmin;
    protected long userId;
    protected String updateType;

    public UpdateHandler(){
    }
    
    public UpdateHandler setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
        return this;
    }

    // сомнительноооооо, ннно окэй
    public BotApiMethodMessage doWork() {
        return processingStrategy.processUpdate();
    }
}
