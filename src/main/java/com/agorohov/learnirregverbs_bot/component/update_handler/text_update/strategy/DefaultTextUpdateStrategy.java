package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class DefaultTextUpdateStrategy implements UpdateProcessingStrategy {

//    private final UpdateHandler uh;
//
//    public DefaultTextUpdateStrategy(UpdateHandler uh) {
//        this.uh = uh;
//    }

    @Override
    public BotApiMethod processUpdate(Update update) {
        String textToSend = "𝕆𝕠𝕡𝕤\n\n"
                + "Нет такой команды.\n"
                + "Используй кнопки или меню бота.\n\n"
                + "Если нужна помощь, загляни в раздел \"Помощь\"";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(update.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("< помощь", "/help")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        return sendMessage.buildNewMessage();
        
//        return updateOrCreateMessage(uh, sendMessage);
    }

}
