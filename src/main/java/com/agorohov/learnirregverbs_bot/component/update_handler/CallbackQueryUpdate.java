package com.agorohov.learnirregverbs_bot.component.update_handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryUpdate extends UpdateHandler {

    public CallbackQueryUpdate(Update update) {
        this.update = update;
        updateType = "CallbackQuery";
    }
}
