package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class AboutTextStrategy implements ProcessingStrategy {
    
    @Override
    public BotApiMethod processUpdate(Update update) {
        String textToSend = "𝔸𝕓𝕠𝕦𝕥\n\n"
                + "Этот бот для изучения неправильных глаголов английского языка - "
                + "проект некоего Александра Горохова.\n\n"
                + "GitHub: https://github.com/agorohovcom/learnirregverbs_bot \n"
                + "Сайт автора: agorohov.com \n"
                + "Резюме автора: [тут будет резюме]\n\n"
                + "Алгоритм работы: [описание как оно работает]\n"
                + "Какая-то ещё инфа: [я инфа]\n\n"
                + "Написать сколько слов всего и как это работает";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(update.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        return sendMessage.buildNewMessage();
        
//        return updateOrCreateMessage(uh, sendMessage);
    }
}
