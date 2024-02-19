package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ButtonsTestTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public ButtonsTestTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "Давай-ка затестим кнопку отмены, или даже парочку!";

        SendMessage message = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("Это отмена", "dismiss")
                .button("И это отмена, брат", "dismiss")
                .endRow()
                .buildNewMessage();

        return message;
    }
}
