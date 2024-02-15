package com.agorohov.learnirregverbs_bot.component.update_handler.text_update_strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

public class DefaultTextUpdate implements UpdateProcessingStrategy{

    @Override
    public BotApiMethodMessage processUpdate() {
        return null;
    }
    
}
