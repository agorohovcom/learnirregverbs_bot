package com.agorohov.learnirregverbs_bot.component.update_handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryUpdate extends UpdateHandler {

    // получаем данные из CallbackQuery
    String callBackData = update.getCallbackQuery().getData();
    int messageId = update.getCallbackQuery().getMessage().getMessageId();
    long chatId = update.getCallbackQuery().getMessage().getChatId();

    public CallbackQueryUpdate(Update update) {
        this.update = update;
    }
}
