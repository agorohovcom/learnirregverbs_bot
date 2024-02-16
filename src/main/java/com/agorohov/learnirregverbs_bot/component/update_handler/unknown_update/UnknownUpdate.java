package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update.strategy.UnknownUpdateStrategy;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownUpdate extends UpdateHandler{
    
    public UnknownUpdate(Update update, boolean isAdmin){
        updateHundlerFieldInitializer(update, isAdmin);
        this.updateType = "Unknow";
        choiseStrategy(update);
    }
    
    private void choiseStrategy(Update update){
        processingStrategy = new UnknownUpdateStrategy(userId);
    }

    @Override
    protected void updateHundlerFieldInitializer(Update update, boolean isAdmin) {
        if (update.hasMessage()){
            this.userId = update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()){
            this.userId = update.getCallbackQuery().getMessage().getChatId();
        }
        
        this.isAdmin = isAdmin;
    }
}