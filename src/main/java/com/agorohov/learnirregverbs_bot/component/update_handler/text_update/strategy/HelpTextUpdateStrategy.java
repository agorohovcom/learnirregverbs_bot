package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class HelpTextUpdateStrategy implements UpdateProcessingStrategy {
    
    private final UpdateHandler uh;

    public HelpTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "Управление ботом осуществляется через меню команд и с помощью кнопок под сообщениями.\n"
                + "Описание пунктов основного меню:\n\n"
                + "/start - главное меню бота;\n"
                + "/learn - учить неправильные глаголы;\n"
                + "/stat - личная статистика;\n"
                + "/about - информация о боте;\n"
                + "/help - описание команд бота и другая помощь.\n\n"
                + "Удалить свою статистику по изучению неправильных глаголов можно в разделе /stat";

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
