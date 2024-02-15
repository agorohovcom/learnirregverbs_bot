package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

// этот класс - исключение, он сразу и наследует UpdateHandler, и реализует UpdateProcessingStrategy
public class UnknownUpdate extends UpdateHandler implements UpdateProcessingStrategy{

    private long chatId;
    
    public UnknownUpdate(Update update){
//        processingStrategy = this;
        this.chatId = update.getMessage().getChatId();
    }
    
    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = "UnknownUpdate";

        SendMessage sendMessage = new MessageBuilder()
                .setChatId(chatId)
                .setText(textToSend)
                .buildNewMessage();
        
        return sendMessage;
    }
}