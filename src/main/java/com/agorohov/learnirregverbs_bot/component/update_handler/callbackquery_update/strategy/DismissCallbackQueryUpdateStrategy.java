package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class DismissCallbackQueryUpdateStrategy implements UpdateProcessingStrategy{
    
    private final UpdateHandler uh;

    public DismissCallbackQueryUpdateStrategy(UpdateHandler uh){
        this.uh = uh;
    }
    
    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "ℂ𝕒𝕟𝕔𝕖𝕝\n\n"
                + "Действие отменено";
        
        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        return updateOrCreateMessage(uh, sendMessage);
    }
}
