package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelpTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "🛟 "    // эмодзи
                + "ℍ𝕖𝕝𝕡\n\n"
                + "🕹 "  // эмодзи
                + "<b>Как управлять ботом?</b>\n"
                + "Кнопками под сообщением, но также доступны текстовые команды:\n\n"
                + "  /start - главное меню бота,\n"
                + "  /learn - учить глаголы,\n"
                + "  /stat - твоя статистика,\n"
                + "  /about - информация о боте,\n"
                + "  /help - помощь (ты сейчас тут).\n\n"
                + "🗿 "  // эмодзи
                + "<b>Как учить глаголы?</b>\n"
                + "  • выбери раздел /learn,\n"
                + "  • запомни три формы глагола,\n"
                + "  • нажми \"Пройти тест\",\n"
                + "  • выбери три формы глагола в правильном порядке, нажав 3 кнопки.\n\n"
                + "♻️ "  // эмодзи
                + "<b>Как обнулить статистику?</b>\n"
                + "Перейди в раздел /stat_reset и подтверди обнуление статистики кнопкой.";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
