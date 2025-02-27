package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class CallbackQueryUpdateHandler implements UpdateHandler {
    
    private static final String UPDATE_TYPE = "CallbackQueryUpdate";
    
    private final CallbackQueryProcessingStrategyFactory strategyFactory;

    @Override
    public BotApiMethod<? extends Serializable> handle(UpdateWrapper wrapper) {
        wrapper.setType(UPDATE_TYPE);
        return strategyFactory.getStrategy(wrapper).processUpdate(wrapper);
    }
}