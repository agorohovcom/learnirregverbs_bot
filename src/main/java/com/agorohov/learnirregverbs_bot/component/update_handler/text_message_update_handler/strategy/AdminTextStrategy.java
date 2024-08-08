package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class AdminTextStrategy implements ProcessingStrategy {

    @Override
    public BotApiMethod processUpdate(Update update, long updateWasReceivedAt, String botId) {
        String textToSend = "𝔸𝕕𝕞𝕚𝕟\n\n"
                + "Приветствую, хозяин!";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(update.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();

        return updateOrCreateMessage(update, sendMessage, updateWasReceivedAt, botId);
    }
}
