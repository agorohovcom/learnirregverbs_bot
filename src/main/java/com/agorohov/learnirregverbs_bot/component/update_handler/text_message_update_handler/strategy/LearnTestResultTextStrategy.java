package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSession;
import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSessionKeeper;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class LearnTestResultTextStrategy implements ProcessingStrategy {

    private final LearnSessionKeeper sessionKeeper;

    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        wrapper.setStrategy(this.getClass().getSimpleName());

        String textToSend = "";

        boolean isSessionExist = sessionKeeper.isExist(wrapper.getMessage().getChatId());

        if (!isSessionExist) {
            textToSend = "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                    + "Время вышло, выбери другое слово.";
        } else {
            LearnSession session = sessionKeeper.get(wrapper.getMessage().getChatId());
            session.saveAnswer(wrapper.getUpdate().getCallbackQuery().getData());
            sessionKeeper.put(session);

            if (!session.isAllAnswersReceived()) {
                wrapper.setExecutable(false);
            } else {
                if (session.isCorrectResult()) {
                    textToSend = "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                    + "Верно!\n\n"
                    + "Результат записан в твою статистику. Продолжим?";
                } else {
                    textToSend = "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                    + "К сожалению, ответ неверный.\n\n"
                    + "Результат записан в твою статистику. Продолжим?";
                }
            }

            
        }

        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Учить следующее слово", "/learn")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();

        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
