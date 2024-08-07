package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class StatResetTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public StatResetTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "ℝ𝕖𝕤𝕖𝕥 𝕤𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                + uh.getUserFirstName() + ", ты собираешься обнулить свою статистику.\n"
                + "Это действие невозможно отменить. Продолжить?";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("Обнулить", "/stat_reset_confirm")
                .button("Отмена", "/dismiss")
                .endRow()
                .row()
                .button("< статистика", "/stat")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();

        if (uh.isUpdatable()) {
            return sendMessage.setMessageId(uh.getMsgId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
