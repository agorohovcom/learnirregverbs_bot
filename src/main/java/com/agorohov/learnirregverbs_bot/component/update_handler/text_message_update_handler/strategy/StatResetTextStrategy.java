package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class StatResetTextStrategy implements ProcessingStrategy {

    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        wrapper.setStrategy(this.getClass().getSimpleName());
        
        String textToSend = "ℝ𝕖𝕤𝕖𝕥 𝕤𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                + "⚠️ " + wrapper.getMessage().getChat().getUserName()
                + ", ты собираешься обнулить свою статистику.\n\n"
                + "Это действие невозможно отменить.\n\n"
                + "Продолжить?";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
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
        
        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
