package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UnknownUpdateStrategy implements ProcessingStrategy {

    @Override
    public BotApiMethod processUpdate(Update update, long updateWasReceivedAt, String botId) {
        String textToSend = "𝕆𝕠𝕡𝕤\n\n"
                + "Такой тип сообщений не поддерживается.\n"
                + "Используй кнопки или меню бота.\n\n"
                + "Если нужна помощь, загляни в раздел \"Помощь\"";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(update.getMessage().getFrom().getId())
                .setText(textToSend)
                .row()
                .button("< помощь", "/help")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        return updateOrCreateMessage(update, sendMessage, updateWasReceivedAt, botId);
    }
}
