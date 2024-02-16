package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update.strategy.UnknownUpdateStrategy;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownUpdate extends UpdateHandler{

    public UnknownUpdate(Update update){
        this.update = update;
        updateType = "Unknown type";
        processingStrategy = new UnknownUpdateStrategy(update);
    }
}