package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DefaultTextUpdateStrategy implements UpdateProcessingStrategy{
    
    private long chatId;
    private String userName;

    public DefaultTextUpdateStrategy(Update update){
        chatId = update.getMessage().getChatId();
        userName = update.getMessage().getChat().getUserName();
    }
    
    @Override
    public BotApiMethodMessage processUpdate() {
        String textToSend = userName + ", нет такой команды.\n"
                + "Если нужна помощь, загляни в раздел /help";
        
        SendMessage sendMessage = new MessageBuilder()
                .setChatId(chatId)
                .setText(textToSend)
                .buildNewMessage();
        
        return sendMessage;
    }
    
}
