package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class AdminTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public AdminTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = "𝔸𝕕𝕞𝕚𝕟\n\n"
                + "Приветствую, хозяин!";

        SendMessage sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .buildNewMessage();

        return sendMessage;
    }
}
