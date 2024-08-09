package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;

@Component
@RequiredArgsConstructor
public class StartTextStrategy implements ProcessingStrategy {

    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        String textToSend = "𝕃𝕖𝕒𝕣𝕟 𝕀𝕣𝕣𝕖𝕘𝕦𝕝𝕒𝕣 𝕍𝕖𝕣𝕓𝕤 𝔹𝕠𝕥\n\n"
                + "Привет, " + wrapper.getMessage().getChat().getUserName() + "!\n\n"
                + "Это бот для изучения неправильных глаголов английского языка.\n\n"
                + "Ты можешь учиться и следить за прогрессом своего обучения.";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Учить неправильные глаголы", "/learn")
                .endRow()
                .row()
                .button("Статистика", "/stat")
                .endRow()
                .row()
                .button("О боте", "/about")
                .endRow()
                .row()
                .button("Помощь", "/help")
                .endRow();
        
        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
