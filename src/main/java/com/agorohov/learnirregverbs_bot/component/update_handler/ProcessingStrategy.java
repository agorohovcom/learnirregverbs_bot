package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface ProcessingStrategy {

    BotApiMethod processUpdate(UpdateWrapper wrapper);

    // проверяем отличается ли новое сообщение от предыдущего,
    // не прошло ли 47 (вообще надо 48) часов с его получения
    // и от бота ли оно.
    // Если да - редактируем предыдущее, если нет - шлём новое
    default BotApiMethod updateOrCreateMessage(UpdateWrapper wrapper, MessageBuilder sendMessage) {
        boolean isNewMsgNotEqOld = wrapper.getMessage().hasText()
                ? !wrapper.getMessage().getText().trim().equals(sendMessage.getText().trim())
                : false;
        boolean isStillTimeToEdit = (System.currentTimeMillis() - wrapper.getUpdateWasReceivedAt()) < (47 * 3600000);

        if (isNewMsgNotEqOld && isStillTimeToEdit && wrapper.isFromBot()) {
            return sendMessage.setMessageId(wrapper.getMessage().getMessageId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
