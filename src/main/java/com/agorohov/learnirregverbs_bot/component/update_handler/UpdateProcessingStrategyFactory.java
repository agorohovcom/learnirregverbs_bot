package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.DefaultTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.StartTextUpdateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UpdateProcessingStrategyFactory {

    private final StartTextUpdateStrategy startTextUpdateStrategy;
    private final DefaultTextUpdateStrategy defaultTextUpdateStrategy;

    public UpdateProcessingStrategy getStrategy(Update update) {
        if (update.getMessage().getText().equals("/start")) {
            return startTextUpdateStrategy;
        } else {
            return defaultTextUpdateStrategy;
        }
    }
}
