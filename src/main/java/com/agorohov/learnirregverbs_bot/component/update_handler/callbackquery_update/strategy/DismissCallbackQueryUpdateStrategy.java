package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DismissCallbackQueryUpdateStrategy implements UpdateProcessingStrategy{
    
    private final long userId;
    private final int messageId;

    public DismissCallbackQueryUpdateStrategy(Update update){
        this.userId = update.getCallbackQuery().getMessage().getChatId();
        this.messageId = update.getCallbackQuery().getMessage().getMessageId();
    }
    
    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "Действие отменено";
        
        EditMessageText message = MessageBuilder
                .create()
                .setChatId(userId)
                .setText(textToSend)
                .setMessageId(messageId)
                .buildUpdateMessage();
        
        return message;
    }
    
}
