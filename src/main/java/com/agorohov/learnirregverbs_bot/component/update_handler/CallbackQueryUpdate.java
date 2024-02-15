package com.agorohov.learnirregverbs_bot.component.update_handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryUpdate extends UpdateHandler {

    private String callBackData;
    private int messageId;
    private long chatId;

    public CallbackQueryUpdate(Update update) {
        callBackData = update.getCallbackQuery().getData();
        messageId = update.getCallbackQuery().getMessage().getMessageId();
        chatId = update.getCallbackQuery().getMessage().getChatId();
    }
}
