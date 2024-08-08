package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;

@Component
@RequiredArgsConstructor
public class StartTextStrategy implements ProcessingStrategy {

    private final VerbService verbService;

    @Override
    public BotApiMethod processUpdate(Update update, long updateWasReceivedAt) {
        String textToSend = "𝕃𝕖𝕒𝕣𝕟 𝕀𝕣𝕣𝕖𝕘𝕦𝕝𝕒𝕣 𝕍𝕖𝕣𝕓𝕤 𝔹𝕠𝕥\n\n"
                + "Привет, " + update.getMessage().getFrom().getFirstName() + "!\n\n"
                + "Это бот для изучения неправильных глаголов английского языка.\n\n"
                + "Ты можешь учиться и следить за прогрессом своего обучения.\n\n"
                + verbService.findById(new Random().nextInt(verbService.getCount()));

        var sendMessage = MessageBuilder
                .create()
                .setChatId(update.getMessage().getFrom().getId())
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
        
        return updateOrCreateMessage(update, sendMessage, updateWasReceivedAt);
    }
}
