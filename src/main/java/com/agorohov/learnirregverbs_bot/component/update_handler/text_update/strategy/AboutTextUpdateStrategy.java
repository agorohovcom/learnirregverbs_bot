package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class AboutTextUpdateStrategy implements UpdateProcessingStrategy {
    
    private final UpdateHandler uh;

    public AboutTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "𝔸𝕓𝕠𝕦𝕥\n\n"
                + "Этот бот для изучения неправильных глаголов английского языка - "
                + "проект некоего Александра Горохова.\n\n"
                + "GitHub: https://github.com/agorohovcom/learnirregverbs_bot \n"
                + "Сайт автора: agorohov.com \n"
                + "Резюме автора: [тут будет резюме]\n\n"
                + "Алгоритм работы: [описание как оно работает]\n"
                + "Какая-то ещё инфа: [я инфа]";

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
