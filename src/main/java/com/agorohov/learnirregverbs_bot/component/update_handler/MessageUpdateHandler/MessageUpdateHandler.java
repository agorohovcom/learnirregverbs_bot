package com.agorohov.learnirregverbs_bot.component.update_handler.MessageUpdateHandler;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategyFactory;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class MessageUpdateHandler implements UpdateHandler {
    
//    private final VerbService verbService;
    private final UpdateProcessingStrategyFactory updateProcessingStrategyFactory;

    @Override
    public BotApiMethod handle(Update update) {
        return updateProcessingStrategyFactory.getStrategy(update).processUpdate(update);
//        String verb = verbService.findById(3).toString();
//        
//        String messageText = update.getMessage().getText() + "\n\n" + verb;
//        long chatId = update.getMessage().getChatId();
//
//        SendMessage message = new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText("You said: " + messageText);
//
//        return message;
    }

}
