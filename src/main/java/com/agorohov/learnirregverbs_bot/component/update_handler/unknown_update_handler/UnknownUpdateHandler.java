package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class UnknownUpdateHandler implements UpdateHandler {
    
    private static final String UPDATE_TYPE = "UnknownUpdate";
    
    private final UnknownProcessingStrategyFactory strategyFactory;
    
    @Override
    public BotApiMethod handle(UpdateWrapper wrapper) {
        wrapper.setType(UPDATE_TYPE);
        return strategyFactory.getStrategy(wrapper).processUpdate(wrapper);
    }
}