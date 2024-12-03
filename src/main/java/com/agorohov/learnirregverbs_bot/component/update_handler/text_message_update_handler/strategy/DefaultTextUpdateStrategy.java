package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class DefaultTextUpdateStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "🌀 "    // эмодзи
                + "𝕆𝕠𝕡𝕤\n\n"
                + "Нет такой команды.\n"
                + "Используй кнопки или меню бота.\n\n"
                + "Если нужна помощь, загляни в раздел \"Помощь\"";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getSupportedMessageOrNull().getChatId())
                .setText(textToSend)
                .row()
                .button("Помощь", "/help")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
