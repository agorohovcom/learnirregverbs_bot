package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.learn_session.*;
import com.agorohov.learnirregverbs_bot.component.learning.test_buttons.*;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class LearnTestTextStrategy implements ProcessingStrategy {

    private final LearnSessionKeeper sessionKeeper;
    private final TestButtonsBuilder buttonsBuilder;

    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        wrapper.setStrategy(this.getClass().getSimpleName());

        String textToSend = null;
        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId());

        // есть ли в LearnSessionKeeper сессия текущего пользователя?
        boolean isSessionExist = sessionKeeper.isExist(wrapper.getMessage().getChatId());

        if (!isSessionExist) {
            textToSend = "𝕋𝕖𝕤𝕥\n\n"
                    + "Время вышло, выбери другое слово.";

            sendMessage
                    .setText(textToSend)
                    .row()
                    .button("< учить другое слово", "/learn")
                    .endRow()
                    .row()
                    .button("<< главное меню", "/start")
                    .endRow();
        } else {
            // достаём сессию, создаем кнопки теста
            LearnSession session = sessionKeeper.get(wrapper.getMessage().getChatId());
            TestButtons testButtons = buttonsBuilder.create(session.getVerb());

            textToSend = "𝕋𝕖𝕤𝕥\n\n"
                    + "Пройдём тест!\n\n"
                    + "Выбери три формы глагола \"" + session.getVerb().getTranslation() + "\" в правильном порядке.";

            sendMessage
                    .setText(textToSend)
                    .row()
                    .button(testButtons.getButtonText(0), testButtons.getCallbackData(0))
                    .button(testButtons.getButtonText(1), testButtons.getCallbackData(1))
                    .button(testButtons.getButtonText(2), testButtons.getCallbackData(2))
                    .endRow()
                    .row()
                    .button(testButtons.getButtonText(3), testButtons.getCallbackData(3))
                    .button(testButtons.getButtonText(4), testButtons.getCallbackData(4))
                    .button(testButtons.getButtonText(5), testButtons.getCallbackData(5))
                    .endRow()
                    .row()
                    .button(testButtons.getButtonText(6), testButtons.getCallbackData(6))
                    .button(testButtons.getButtonText(7), testButtons.getCallbackData(7))
                    .button(testButtons.getButtonText(8), testButtons.getCallbackData(8))
                    .endRow()
                    .row()
                    .button(testButtons.getButtonText(9), testButtons.getCallbackData(9))
                    .button(testButtons.getButtonText(10), testButtons.getCallbackData(10))
                    .button(testButtons.getButtonText(11), testButtons.getCallbackData(11))
                    .endRow()
                    .row()
                    .button("< учить другое слово", "/learn")
                    .endRow()
                    .row()
                    .button("<< главное меню", "/start")
                    .endRow();
        }

        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
