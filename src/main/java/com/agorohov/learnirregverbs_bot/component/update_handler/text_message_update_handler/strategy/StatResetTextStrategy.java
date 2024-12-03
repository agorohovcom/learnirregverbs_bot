package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatResetTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "📊 "    // эмодзи
                + "ℝ𝕖𝕤𝕖𝕥 𝕤𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                + wrapper.getSupportedMessageOrNull().getChat().getUserName()
                + ", ты собираешься обнулить свою статистику.\n\n"
                + "⚠️ "  // эмодзи
                + "<b>Это действие невозможно отменить!</b>\n\n️"
                + "Продолжить?";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getSupportedMessageOrNull().getChatId())
                .setText(textToSend)
                .row()
                .button("Обнулить", "/stat_reset_confirm")
                .button("Отмена", "/stat")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
