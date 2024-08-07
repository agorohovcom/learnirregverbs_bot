package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface UpdateProcessingStrategy {

    BotApiMethod processUpdate();

    default BotApiMethod updateOrCreateMessage(UpdateHandler uh, MessageBuilder sendMessage) {
        if (uh.isUpdatable(sendMessage.getText())) {
            return sendMessage.setMessageId(uh.getMsgId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
