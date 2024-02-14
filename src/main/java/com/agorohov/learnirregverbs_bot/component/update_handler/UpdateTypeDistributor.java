package com.agorohov.learnirregverbs_bot.component.update_handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateTypeDistributor {

    public UpdateHandler distribute(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                return new TextUpdate(update);
            }
            else if (update.hasCallbackQuery()) {
                return new CallbackQueryUpdate(update);
            }

        }
        return null;
    }
}
