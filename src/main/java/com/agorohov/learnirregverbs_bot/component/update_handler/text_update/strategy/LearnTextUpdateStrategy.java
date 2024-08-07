package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class LearnTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public LearnTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "𝕃𝕖𝕒𝕣𝕟\n\n"
                + uh.getUserFirstName() + ", начнем учиться!";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("Учить 5 глаголов", "/learn_start")
                .endRow()
                .row()
                .button("Повторить выученные", "/learn_repeat")
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
