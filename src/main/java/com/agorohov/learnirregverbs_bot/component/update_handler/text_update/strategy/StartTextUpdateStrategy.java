package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public StartTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "Привет, " + uh.getUserFirstName() + "!\n\n"
                + "Я бот для изучения неправильных глаголов английского языка.\n"
                + "Ты можешь учить неправильные глаголы, "
                + "повторять уже изученные, просматривать статистику своего обучения.\n"
                + "Если хочешь, можно сбросить личный прогресс и начать заново.\n\n"
                + "Выбери необходимый раздел:";
        
//        SendMessage sendMessage = MessageBuilder
//                .create()
//                .setChatId(uh.getUserId())
//                .setText(textToSend)
//                .row()
//                .button("Старт", "/start")
//                .endRow()
//                .row()
//                .button("Учить", "/learn")
//                .endRow()
//                .buildNewMessage();
        
        // это я пытался сделать, чтобы команда /start по возможности 
        // редактировала предыдущее сообщение, но не получается
        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("Учить", "/learn")
                .endRow()
                .row()
                .button("Статистика", "/statistics")
                .endRow();
        
//        System.out.println("isUpdatable(): " + uh.isUpdatable(uh.getMessage().getText()));
        
        if(uh.isUpdatable()){
            return sendMessage.setMessageId(uh.getMsgId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
        

//        return sendMessage;
    }
}
