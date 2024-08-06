package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class LearnTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public LearnTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = uh.getUserFirstName() + ", начнем учиться!";

//        EditMessageText sendMessage = MessageBuilder
//                .create()
//                .setChatId(uh.getUserId())
//                .setText(textToSend)
//                .setMessageId(uh.getMsgId())
//                .row()
//                .button("Учить", "learn_start")
//                .endRow()
//                .row()
//                .button("Повторить выученные", "learn_repeat")
//                .endRow()
//                .row()
//                .button("<< главное меню", "/start")
//                .endRow()
//                .buildUpdateMessage();

//        return sendMessage;

        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("Учить", "learn_start")
                .endRow()
                .row()
                .button("Повторить выученные", "learn_repeat")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        if(uh.isUpdatable()) {
            return sendMessage.setMessageId(uh.getMsgId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
