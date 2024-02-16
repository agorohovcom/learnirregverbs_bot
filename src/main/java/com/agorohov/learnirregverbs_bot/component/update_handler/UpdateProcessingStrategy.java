package com.agorohov.learnirregverbs_bot.component.update_handler;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;

public interface UpdateProcessingStrategy {
    BotApiMethodMessage processUpdate();
//    String getStrategyName();
}
