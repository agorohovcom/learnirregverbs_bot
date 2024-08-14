package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnknownUpdateStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "𝕆𝕠𝕡𝕤\n\n"
                + "Такой тип сообщений не поддерживается.\n"
                + "Используй кнопки или меню бота.\n\n"
                + "Если нужна помощь, загляни в раздел \"Помощь\"";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getFrom().getId())
                .setText(textToSend)
                .row()
                .button("< помощь", "/help")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
