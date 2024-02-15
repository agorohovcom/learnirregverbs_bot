package com.agorohov.learnirregverbs_bot.component.update_handler.text_update_strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DefaultTextUpdate implements UpdateProcessingStrategy{
    
    private long chatId;

    public DefaultTextUpdate(Update update){
        chatId = update.getMessage().getChatId();
    }
    
    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = "шо? нет такой команды";
        
        SendMessage sendMessage = new MessageBuilder()
                .setChatId(chatId)
                .setText(textToSend)
                .buildNewMessage();
        
        return sendMessage;
    }
    
}
