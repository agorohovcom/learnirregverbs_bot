package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

public interface ProcessingStrategy {

    BotApiMethod<? extends Serializable> processUpdate(UpdateWrapper wrapper);

    /**
     * Проверяем отличается ли новое сообщение от предыдущего,
     * не прошло ли 47 (вообще надо 48) часов с его получения
     * и является ли оно CallbackQuery.
     * Если да - редактируем предыдущее, если нет - шлём новое
     */
    default BotApiMethod<? extends Serializable> updateOrCreateMessage(
            UpdateWrapper wrapper,
            MessageBuilder sendMessage) {
        boolean isNewMsgNotEqOld = wrapper.getSupportedMessageOrNull().hasText()
                && !wrapper.getSupportedMessageOrNull().getText().trim().equals(sendMessage.getText().trim());
        boolean isStillTimeToEdit = (System.currentTimeMillis() - wrapper.getUpdateWasReceivedAt()) < (47 * 3600000);

        if (isNewMsgNotEqOld && isStillTimeToEdit && wrapper.getUpdate().hasCallbackQuery()) {
            return sendMessage.setMessageId(wrapper.getSupportedMessageOrNull().getMessageId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
