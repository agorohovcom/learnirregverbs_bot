package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface ProcessingStrategy {

    BotApiMethod processUpdate(UpdateWrapper wrapper);

    // этот метод проверяет, можно ли редактировать предыдущее сообщение,
    // и дальше редактирует или создаёт новое сообщение
    default BotApiMethod updateOrCreateMessage(UpdateWrapper wrapper, MessageBuilder sendMessage) {
        boolean isNewMsgNotEqOld = wrapper.getUpdate().hasMessage() && wrapper.getMessage().hasText()
                ? !wrapper.getMessage().getText().equals(sendMessage.getText())
                : false;
        boolean isStillTimeToEdit = (System.currentTimeMillis() - wrapper.getUpdateWasReceivedAt()) < (47 * 3600000);
        
        if (isNewMsgNotEqOld && isStillTimeToEdit && wrapper.isFromBot()) {
            return sendMessage.setMessageId(wrapper.getMessage().getMessageId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
