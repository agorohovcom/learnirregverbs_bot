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
        
        // Это сообщение не будет отправлено
        // Пока решил вопрос с этим типом Update так
        // По хорошему надо убрать пустые действия
        wrapper.setExecutable(false);

        String textToSend = "🌀 " // эмодзи
                + "𝕆𝕠𝕡𝕤\n\n"
                + "Такой тип сообщений не поддерживается.\n"
                + "Используй кнопки или меню бота.\n\n"
                + "Если нужна помощь, загляни в раздел \"Помощь\"";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getFrom().getId())
                .setText(textToSend)
                .row()
                .button("Помощь", "/help")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
