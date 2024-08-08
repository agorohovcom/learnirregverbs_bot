package com.agorohov.learnirregverbs_bot.component.update_handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

    BotApiMethod handle(Update update, long updateWasReceivedAt);
}
