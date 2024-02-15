package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.text_update_strategy.*;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownUpdate extends UpdateHandler implements UpdateProcessingStrategy{

    public UnknownUpdate(Update update){}
    
    @Override
    public BotApiMethodMessage processUpdate() {
        return null;
    }
}
