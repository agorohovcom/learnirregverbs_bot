package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ProcessingStrategy {

    BotApiMethod processUpdate(Update update, long updateWasReceivedAt);

    default BotApiMethod updateOrCreateMessage(Update update, MessageBuilder sendMessage, long updateWasReceivedAt) {
        String newMsg = update.getMessage().getText();
        boolean stillTime = (System.currentTimeMillis() - updateWasReceivedAt) < (48 * 3600000);

        if (!newMsg.equals(sendMessage.getText()) && stillTime && true) {
            return sendMessage.setMessageId(update.getMessage().getMessageId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
