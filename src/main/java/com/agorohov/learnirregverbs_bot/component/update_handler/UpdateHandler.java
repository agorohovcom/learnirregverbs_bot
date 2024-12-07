package com.agorohov.learnirregverbs_bot.component.update_handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

public interface UpdateHandler {

    BotApiMethod<? extends Serializable> handle(UpdateWrapper wrapper);
}
