package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class StatTextStrategy implements ProcessingStrategy {
    
    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        String textToSend = "𝕊𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                + wrapper.getMessage().getFrom().getFirstName() + ", а вот и твоя статистика.\n"
                + "Тут вкратце объяснено по статистике. Здорово, правда?";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Обнулить статистику", "/stat_reset")
                .endRow()
                .row()
                .button("< учить", "/learn")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
        
        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
