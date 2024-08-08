package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class TextMessageUpdateHandler implements UpdateHandler {

    private final TextProcessingStrategyFactory strategyFactory;

    @Override
    public BotApiMethod handle(UpdateWrapper wrapper) {
        return strategyFactory.getStrategy(wrapper).processUpdate(wrapper);
    }
}
