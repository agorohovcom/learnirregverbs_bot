package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class FailCallbackQueryStrategy implements ProcessingStrategy{
    
    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        wrapper.setStrategy(this.getClass().getSimpleName());
        
        String textToSend = "𝕆𝕠𝕡𝕤\n\n"
                + "Извини, произошла внутренняя ошибка.";
        
        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
