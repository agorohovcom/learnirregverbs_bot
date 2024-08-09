package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update_handler.CallbackQueryUpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.TextMessageUpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update_handler.UnknownUpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateHandlerFactory {

    private final TextMessageUpdateHandler messageUpdateHandler;
    private final CallbackQueryUpdateHandler callbackQueryUpdateHandler;
    private final UnknownUpdateHandler unknownUpdateHandler;

    public UpdateHandler getHandler(UpdateWrapper wrapper) {
        if (wrapper.getUpdate().hasMessage() && wrapper.getUpdate().getMessage().hasText()) {
            return messageUpdateHandler;
        } else if (wrapper.getUpdate().hasCallbackQuery()) {
            return callbackQueryUpdateHandler;
        } else {
            return unknownUpdateHandler;
        }
    }
}
