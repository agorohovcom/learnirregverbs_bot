package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AdminTextUpdateStrategy implements UpdateProcessingStrategy {

    private final long userId;
    private final int messageId;

    public AdminTextUpdateStrategy(Update update) {
        this.userId = update.getMessage().getChatId();
        this.messageId = update.getMessage().getMessageId();
    }

    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = "Приветствую, хозяин!";

        SendMessage sendMessage = MessageBuilder
                .create()
                .setChatId(userId)
                .setText(textToSend)
                .buildNewMessage();

        return sendMessage;
    }
}
