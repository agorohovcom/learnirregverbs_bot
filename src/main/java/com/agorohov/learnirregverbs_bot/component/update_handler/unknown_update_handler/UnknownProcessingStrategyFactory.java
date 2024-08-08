package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler.strategy.UnknownUpdateStrategy;

@Component
@RequiredArgsConstructor
public class UnknownProcessingStrategyFactory {

    private final UnknownUpdateStrategy unknownUpdateStrategy;

    public ProcessingStrategy getStrategy(Update update) {
        return unknownUpdateStrategy;
    }
}
