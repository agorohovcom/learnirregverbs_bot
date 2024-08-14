package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelpTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "ℍ𝕖𝕝𝕡\n\n"
                + "Управление ботом осуществляется через меню команд и с помощью кнопок под сообщениями.\n"
                + "Описание пунктов основного меню:\n\n"
                + "/start - главное меню бота;\n"
                + "/learn - учить неправильные глаголы;\n"
                + "/stat - личная статистика;\n"
                + "/about - информация о боте;\n"
                + "/help - описание команд бота и другая помощь.\n\n"
                + "Удалить свою статистику по изучению неправильных глаголов можно в разделе /stat";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
