package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartTextUpdateStrategy implements UpdateProcessingStrategy {

    private final long userId;
    private final String userFirstName;
    private final String msgBody;

    public StartTextUpdateStrategy(Update update) {
        this.userId = update.getMessage().getChatId();
        this.userFirstName = update.getMessage().getChat().getFirstName();
        this.msgBody = update.getMessage().getText();
    }

    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = "Привет, " + userFirstName + "!";

        SendMessage sendMessage = MessageBuilder
                .create()
                .setChatId(userId)
                .setText(textToSend)
                .buildNewMessage();
        
        return sendMessage;
    }
}
