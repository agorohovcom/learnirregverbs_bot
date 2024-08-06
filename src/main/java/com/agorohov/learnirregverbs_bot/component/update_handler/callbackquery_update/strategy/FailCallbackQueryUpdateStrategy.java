package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class FailCallbackQueryUpdateStrategy implements UpdateProcessingStrategy{
    
    private final UpdateHandler uh;

    public FailCallbackQueryUpdateStrategy(UpdateHandler uh){
        this.uh = uh;
    }
    
    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "Извини, произошла внутренняя ошибка.";
        
        EditMessageText message = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .setMessageId(uh.getMsgId())
                .row()
                .button("<< главное меню", "/start")
                .endRow()
                .buildUpdateMessage();
        
        return message;
    }
}
