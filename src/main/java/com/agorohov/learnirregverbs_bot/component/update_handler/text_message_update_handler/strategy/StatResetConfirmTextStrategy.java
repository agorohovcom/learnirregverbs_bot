package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class StatResetConfirmTextStrategy implements ProcessingStrategy {

    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        String textToSend = "ℝ𝕖𝕤𝕖𝕥 𝕤𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                + wrapper.getMessage().getFrom().getFirstName() + ", твоя статистика изучения "
                + "неправильных глаголов удалена.\n\n"
                + "Но ты можешь начать заново!";

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
