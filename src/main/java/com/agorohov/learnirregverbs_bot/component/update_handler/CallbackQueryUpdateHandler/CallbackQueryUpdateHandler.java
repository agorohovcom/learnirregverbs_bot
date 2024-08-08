package com.agorohov.learnirregverbs_bot.component.update_handler.CallbackQueryUpdateHandler;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CallbackQueryUpdateHandler implements UpdateHandler {

    @Override
    public BotApiMethod handle(Update update) {
        return null;
    }

}
