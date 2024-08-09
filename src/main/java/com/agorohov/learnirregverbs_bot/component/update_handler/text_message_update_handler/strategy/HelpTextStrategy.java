package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class HelpTextStrategy implements ProcessingStrategy {

    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        wrapper.setStrategy(this.getClass().getSimpleName());
        
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
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        return updateOrCreateMessage(wrapper, sendMessage);
    }

}
