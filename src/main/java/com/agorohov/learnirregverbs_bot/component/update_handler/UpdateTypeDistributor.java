package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.CallbackQueryUpdate;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.TextUpdate;
import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update.UnknownUpdate;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateTypeDistributor {

    public static UpdateHandler distribute(Update update) {
        if (/** update.hasMessage() && */update.getMessage().hasText()) {
            return new TextUpdate(update);
        } else if (update.hasCallbackQuery()) {
            return new CallbackQueryUpdate(update);
        } else {
            return new UnknownUpdate(update);
        }
    }
}