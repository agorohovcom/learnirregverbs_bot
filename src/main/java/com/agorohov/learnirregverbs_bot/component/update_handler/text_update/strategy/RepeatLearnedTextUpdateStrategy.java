package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class RepeatLearnedTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public RepeatLearnedTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "ℝ𝕖𝕡𝕖𝕒𝕥 𝕝𝕖𝕒𝕣𝕟𝕖𝕕\n\n"
                + uh.getUserFirstName() + ", пока нет изученных слов, "
                + "которые можно повторять.";

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
