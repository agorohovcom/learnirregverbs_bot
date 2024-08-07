package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class StartTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;

    public StartTextUpdateStrategy(UpdateHandler uh) {
        this.uh = uh;
    }

    @Override
    public BotApiMethod processUpdate() {
        String textToSend = "𝕃𝕖𝕒𝕣𝕟 𝕀𝕣𝕣 𝕍𝕖𝕣𝕓𝕤 𝔹𝕠𝕥\n\n"
                + "Привет, " + uh.getUserFirstName() + "!\n\n"
                + "Это бот для изучения неправильных глаголов английского языка.\n\n"
                + "Ты можешь учиться и следить за прогрессом своего обучения.\n\n"
                + "Выбери необходимый раздел:";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("Учить", "/learn")
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

        if (uh.isUpdatable()) {
            return sendMessage.setMessageId(uh.getMsgId()).buildUpdateMessage();
        } else {
            return sendMessage.buildNewMessage();
        }
    }
}
