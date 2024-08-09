package com.agorohov.learnirregverbs_bot.component.update_handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface UpdateHandler {

    BotApiMethod handle(UpdateWrapper wrapper);
}
