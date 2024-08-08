package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class LearnTextStrategy implements ProcessingStrategy {

    @Override
    public BotApiMethod processUpdate(Update update, long updateWasReceivedAt, String botId) {

        String textToSend = "𝕃𝕖𝕒𝕣𝕟\n\n"
                + update.getMessage().getFrom().getFirstName() + ", начнём учиться!\n\n"
                + "Перед тобой 5 случайных неправильных глаголов в трёх формах "
                + "и с переводом. Постарайся их запомнить, чтобы пройти тест.\n\n"
                + "Когда будешь готов, нажми \"Пройти тест\"";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(update.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Пройти тест", "/learn_test")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();

        return updateOrCreateMessage(update, sendMessage, updateWasReceivedAt, botId);
    }
}
