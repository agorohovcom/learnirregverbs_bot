package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class FailCallbackQueryUpdateStrategy implements UpdateProcessingStrategy{
    
    private final UpdateHandler uh;

    public FailCallbackQueryUpdateStrategy(UpdateHandler uh){
        this.uh = uh;
    }
    
    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "𝕆𝕠𝕡𝕤\n\n"
                + "Извини, произошла внутренняя ошибка.";
        
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
