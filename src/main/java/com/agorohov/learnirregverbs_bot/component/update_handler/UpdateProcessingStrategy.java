package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateProcessingStrategy {

    BotApiMethod processUpdate(Update update);

//    default BotApiMethod updateOrCreateMessage(Update update, MessageBuilder sendMessage) {
//        if (uh.isUpdatable(sendMessage.getText())) {
//            return sendMessage.setMessageId(uh.getMsgId()).buildUpdateMessage();
//        } else {
//            return sendMessage.buildNewMessage();
//        }
//    }
}
