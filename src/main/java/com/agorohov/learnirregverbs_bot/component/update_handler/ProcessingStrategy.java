package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ProcessingStrategy {

    BotApiMethod processUpdate(Update update, long updateWasReceivedAt, String botId);

    // этот метод проверяет, можно ли редактировать предыдущее сообщение,
    // и дальше редактирует или создаёт новое сообщение
    default BotApiMethod updateOrCreateMessage(Update update, MessageBuilder sendMessage, long updateWasReceivedAt, String botId) {
        boolean isNewMsgNotEqOld = update.getMessage().hasText()
                ? !update.getMessage().getText().equals(sendMessage.getText())
                : false;
        boolean isStillTimeToEdit = (System.currentTimeMillis() - updateWasReceivedAt) < (47 * 3600000);
        boolean isMessageFromBot = update.getMessage().getFrom().getId().equals(botId);
        if (isNewMsgNotEqOld && isStillTimeToEdit && isMessageFromBot) {
            return sendMessage.setMessageId(update.getMessage().getMessageId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
