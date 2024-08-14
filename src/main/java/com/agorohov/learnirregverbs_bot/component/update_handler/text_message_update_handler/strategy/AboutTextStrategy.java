package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AboutTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "ℹ️ "    // эмодзи
                + "𝔸𝕓𝕠𝕦𝕥\n\n"
                + "Этот бот для изучения неправильных глаголов английского языка - "
                + "проект некоего Александра Горохова.\n\n"
                + "GitHub: https://github.com/agorohovcom/learnirregverbs_bot \n"
                + "Сайт автора: agorohov.com \n"
                + "Резюме автора: [тут будет резюме]\n\n"
                + "Алгоритм работы: [описание как оно работает]\n"
                + "Какая-то ещё инфа: [я инфа]\n\n"
                + "Написать сколько слов всего и как это работает";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
