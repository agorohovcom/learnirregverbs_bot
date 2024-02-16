package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UnknownUpdateStrategy implements UpdateProcessingStrategy {

    private long userId;
    
    public UnknownUpdateStrategy(long userId) {
        this.userId = userId;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "Такой тип сообщений не поддерживается.\n"
                + "Если нужна помощь, загляни в раздел /help";

        SendMessage sendMessage = MessageBuilder
                .create()
                .setChatId(userId)
                .setText(textToSend)
                .buildNewMessage();
        
        return sendMessage;
    }
}