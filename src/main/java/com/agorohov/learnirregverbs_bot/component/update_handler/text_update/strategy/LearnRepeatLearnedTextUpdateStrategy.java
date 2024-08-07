package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class LearnRepeatLearnedTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public LearnRepeatLearnedTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "ℝ𝕖𝕡𝕖𝕒𝕥 𝕝𝕖𝕒𝕣𝕟𝕖𝕕\n\n"
                + uh.getUserFirstName()
                + ", тут ты можешь повторить глаголы, по которым правильно прошёл тест несколько раз подряд.\n\n"
                + "Пока нет выученных слов, "
                + "которые можно повторить.";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("< учить", "/learn")
                .endRow()
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
