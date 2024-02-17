package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update.strategy.UnknownUpdateStrategy;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class UnknownUpdate extends UpdateHandler {

    public UnknownUpdate(Update update, boolean isAdmin) {
        updateHundlerFieldInitializer(update, isAdmin);
        this.updateType = "Unknow";
        choiseStrategy(userId);
        
    }

    private void choiseStrategy(long userId) {
        processingStrategy = new UnknownUpdateStrategy(userId);
    }

    @Override
    protected void updateHundlerFieldInitializer(Update update, boolean isAdmin) {
        this.userId = update.hasMessage() 
                ? update.getMessage().getChatId() 
                : update.getCallbackQuery().getMessage().getChatId();
        
        this.msgId = update.hasMessage() 
                ? update.getMessage().getMessageId() 
                : update.getCallbackQuery().getMessage().getMessageId();
        
        this.isAdmin = isAdmin;
    }
}
