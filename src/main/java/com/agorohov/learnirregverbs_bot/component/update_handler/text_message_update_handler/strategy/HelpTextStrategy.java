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
                + "Управление ботом осуществляется с помощью кнопок под сообщением. "
                + "Также есть возможность использовать текстовые команды:\n\n"
                + "/start - главное меню бота\n"
                + "/learn - учить неправильные глаголы\n"
                + "/stat - твоя статистика\n"
                + "/about - информация о боте\n"
                + "/help - описание команд бота и другая помощь\n\n"
                + "Ты можешь обнулить свою статистику в разделе \"Статистика\" или по команде /stat_reset.";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
