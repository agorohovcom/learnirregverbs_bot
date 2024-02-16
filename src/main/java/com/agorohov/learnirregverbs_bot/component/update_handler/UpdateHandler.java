package com.agorohov.learnirregverbs_bot.component.update_handler;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Configuration
@Getter
@PropertySource("application.yaml")
public abstract class UpdateHandler {
    
    protected Update update;
    
    protected UpdateProcessingStrategy processingStrategy;

    // сомнительноооооо, ннно окэй
    public BotApiMethodMessage doWork() {
        return processingStrategy.processUpdate();
    }

    @Value("${bot.owner}")
    protected String botOwner;
    
    protected String updateType;
//    protected String msgToLog = updateType + " update was recived (updateId=" + update.getUpdateId() + ").";
    
    public UpdateHandler(){}
}
