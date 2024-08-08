package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class LearnTestTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public LearnTestTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "𝕋𝕖𝕤𝕥\n\n"
                + "Тут будет тест";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("< учить другие слова", "/learn")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();

        return updateOrCreateMessage(uh, sendMessage);
    }
}
