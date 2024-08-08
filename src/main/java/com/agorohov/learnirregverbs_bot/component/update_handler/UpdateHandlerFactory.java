package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.CallbackQueryUpdateHandler.CallbackQueryUpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.MessageUpdateHandler.MessageUpdateHandler;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UpdateHandlerFactory {

    private final MessageUpdateHandler messageUpdateHandler;
    private final CallbackQueryUpdateHandler callbackQueryUpdateHandler;

    public UpdateHandler getHandler(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return messageUpdateHandler;
        } else {
            return callbackQueryUpdateHandler;
        }
    }
}
