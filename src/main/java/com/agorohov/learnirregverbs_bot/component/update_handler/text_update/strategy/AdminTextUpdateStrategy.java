package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AdminTextUpdateStrategy implements UpdateProcessingStrategy {

    // убрать лишние поля для этого класса
    private Message userMessage;
    private Chat chat;
    private long chatId;
    private int messageId;
    private String userName;
    private String msgBody;

    public AdminTextUpdateStrategy(Update update) {
        userMessage = update.getMessage();
        chat = userMessage.getChat();
        chatId = userMessage.getChatId();
        messageId = userMessage.getMessageId();
        userName = chat.getUserName();
        msgBody = userMessage.getText();
    }

    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = "Приветствую, создатель " + userName + "!\n\n";

        SendMessage sendMessage = new MessageBuilder()
                .setChatId(chatId)
                .setText(textToSend)
                .buildNewMessage();
        
        return sendMessage;
    }
}
