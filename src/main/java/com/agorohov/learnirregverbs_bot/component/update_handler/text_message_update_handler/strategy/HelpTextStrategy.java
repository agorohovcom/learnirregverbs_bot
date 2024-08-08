package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class HelpTextStrategy implements ProcessingStrategy {

    @Override
    public BotApiMethod processUpdate(Update update) {
        String textToSend = "ℍ𝕖𝕝𝕡\n\n"
                + "Управление ботом осуществляется через меню команд и с помощью кнопок под сообщениями.\n"
                + "Описание пунктов основного меню:\n\n"
                + "/start - главное меню бота;\n"
                + "/learn - учить неправильные глаголы;\n"
                + "/stat - личная статистика;\n"
                + "/about - информация о боте;\n"
                + "/help - описание команд бота и другая помощь.\n\n"
                + "Удалить свою статистику по изучению неправильных глаголов можно в разделе /stat";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(update.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        return sendMessage.buildNewMessage();

//        return updateOrCreateMessage(uh, sendMessage);
    }

}
