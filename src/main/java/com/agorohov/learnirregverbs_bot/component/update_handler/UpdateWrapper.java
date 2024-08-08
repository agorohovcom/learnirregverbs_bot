package com.agorohov.learnirregverbs_bot.component.update_handler;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
public class UpdateWrapper {

    private final Update update;
    private final long updateWasReceivedAt;
    private final boolean isFromBot;
    private final boolean isAdmin;

    public Message getMessage() {
        return update.hasMessage()
                ? update.getMessage()
                : update.getCallbackQuery().getMessage();
    }
}
