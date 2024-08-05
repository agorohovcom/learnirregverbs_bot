package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class LearnTextUpdateStrategy implements UpdateProcessingStrategy{
    
    private final UpdateHandler uh;

    public LearnTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = uh.getUserFirstName() + ", начнем учиться!";

        SendMessage sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("Учить", "learn_start")
                .endRow()
                .row()
                .button("Повторить выученные", "learn_repeat")
                .endRow()
                .buildNewMessage();
        
        return sendMessage;
    }
}
