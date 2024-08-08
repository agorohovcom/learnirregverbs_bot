package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TextMessageUpdateHandler implements UpdateHandler {

    private final TextProcessingStrategyFactory strategyFactory;

    @Override
    public BotApiMethod handle(Update update, long updateWasReceivedAt, String botId) {
        return strategyFactory.getStrategy(update).processUpdate(update, updateWasReceivedAt, botId);
    }
}
