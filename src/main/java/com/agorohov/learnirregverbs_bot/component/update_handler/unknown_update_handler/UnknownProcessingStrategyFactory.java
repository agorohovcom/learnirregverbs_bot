package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler.strategy.UnknownUpdateStrategy;

@Component
@RequiredArgsConstructor
public class UnknownProcessingStrategyFactory {

    private final UnknownUpdateStrategy unknownUpdateStrategy;

    public ProcessingStrategy getStrategy(UpdateWrapper wrapper) {
        return unknownUpdateStrategy;
    }
}
