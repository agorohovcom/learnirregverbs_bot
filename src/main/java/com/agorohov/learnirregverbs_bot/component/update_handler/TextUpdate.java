package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.text_updates.*;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TextUpdate extends UpdateHandler {

    // получаем необходимые данные из сообщения
    private Message userMessage = update.getMessage();
    private Chat chat = userMessage.getChat();
    private long chatId = userMessage.getChatId();
    private String userName = chat.getUserName();
    private String msgBody = userMessage.getText();

    public TextUpdate(Update update) {
        this.update = update;

        switch(msgBody){
            case "/start":
                processingStrategy = new StartTextUpdate();
                break;
            default:
                break;
        }
    }

}