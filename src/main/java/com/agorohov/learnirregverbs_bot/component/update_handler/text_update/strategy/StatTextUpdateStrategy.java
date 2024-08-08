package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class StatTextUpdateStrategy implements UpdateProcessingStrategy {
    
    private final UpdateHandler uh;

    public StatTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "𝕊𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                + uh.getUserFirstName() + ", а вот и твоя статистика.\n"
                + "Тут вкратце объяснено по статистике. Здорово, правда?";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
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
        
        return updateOrCreateMessage(uh, sendMessage);
    }
}
