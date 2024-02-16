package com.agorohov.learnirregverbs_bot.component.update_handler;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public abstract class UpdateHandler {

    protected long userId;
//    protected String userName;
//    protected String userFirstName;

    protected int msgId;
//    protected String msgBody;

//    protected String callBaclQueryData;

    protected boolean isAdmin;

    protected String updateType;

    protected UpdateProcessingStrategy processingStrategy;

    // сомнительноооооо, ннно окэй (я про возвращаемый тип)
    public BotApiMethod doWork() {
        return processingStrategy.processUpdate();
    }
    
    protected abstract void updateHundlerFieldInitializer(Update update, boolean isAdmin);
}
