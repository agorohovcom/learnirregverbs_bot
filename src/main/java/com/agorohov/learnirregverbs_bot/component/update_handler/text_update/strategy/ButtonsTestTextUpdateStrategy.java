package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ButtonsTestTextUpdateStrategy implements UpdateProcessingStrategy {

    // убрать лишние поля для этого класса
    private final long userId;
    private final int messageId;
    private final String userFirstName;
    private final String msgBody;

    public ButtonsTestTextUpdateStrategy(Update update) {
        this.userId = update.getMessage().getChatId();
        this.messageId = update.getMessage().getMessageId();
        this.userFirstName = update.getMessage().getChat().getFirstName();
        this.msgBody = update.getMessage().getText();
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "Давай-ка затестим кнопку отмены, или даже парочку!";

        SendMessage message = MessageBuilder.create()
                                .setChatId(userId)
                                .setText(textToSend)
                                .row()
                                .button("Это отмена", "dismiss")
                                .button("И это отмена, брат", "dismiss")
                                .endRow()
                                .buildNewMessage();
        
        return message;
    }
}
