package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownUpdateStrategy implements UpdateProcessingStrategy {

    private long chatId;
    private String userName;
    
    public UnknownUpdateStrategy(Update update) {
        chatId = update.getMessage().getChatId();
        userName = update.getMessage().getChat().getUserName();
    }

    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = userName + ", данный тип сообщений не поддерживается.\n"
                + "Если нужна помощь, загляни в раздел /help";

        SendMessage sendMessage = new MessageBuilder()
                .setChatId(chatId)
                .setText(textToSend)
                .buildNewMessage();
        
        return sendMessage;
    }
}
