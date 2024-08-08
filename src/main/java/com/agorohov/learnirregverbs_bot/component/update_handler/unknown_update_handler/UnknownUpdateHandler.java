package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UnknownUpdateHandler implements UpdateHandler {
    
    private final UnknownProcessingStrategyFactory strategyFactory;
    
    @Override
    public BotApiMethod handle(Update update, long updateWasReceivedAt) {
        return strategyFactory.getStrategy(update).processUpdate(update, updateWasReceivedAt);
    }
}