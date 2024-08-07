package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class DefaultTextUpdateStrategy implements UpdateProcessingStrategy{
    
    private final UpdateHandler uh;

    public DefaultTextUpdateStrategy(UpdateHandler  uh){
        this.uh = uh;
    }
    
//    @Override
//    public BotApiMethodMessage processUpdate() {
//        String textToSend = "Нет такой команды.\n"
//                + "Используй кнопки или меню бота.\n\n"
//                + "Если нужна помощь, загляни в раздел /help";
//        
//        SendMessage sendMessage = MessageBuilder
//                .create()
//                .setChatId(uh.getUserId())
//                .setText(textToSend)
//                .buildNewMessage();
//        
//        return sendMessage;
//    }
    
    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "𝕆𝕠𝕡𝕤\n\n"
                + "Нет такой команды.\n"
                + "Используй кнопки или меню бота.\n\n"
                + "Если нужна помощь, загляни в раздел /help";
        
        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
         if (uh.isUpdatable()) {
            return sendMessage.setMessageId(uh.getMsgId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
    
}
