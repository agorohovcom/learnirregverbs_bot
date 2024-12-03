package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSession;
import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSessionKeeper;
import com.agorohov.learnirregverbs_bot.component.learning.test_buttons.TestButtons;
import com.agorohov.learnirregverbs_bot.component.learning.test_buttons.TestButtonsBuilder;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LearnTestTextStrategy extends ProcessingStrategyAbstractImpl {

    private final LearnSessionKeeper sessionKeeper;
    private final TestButtonsBuilder buttonsBuilder;

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "";
        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getSupportedMessageOrNull().getChatId());

        if (!sessionKeeper.isExists(wrapper.getSupportedMessageOrNull().getChatId())) {
            textToSend = "🎓 " // эмодзи
                    + "𝕋𝕖𝕤𝕥\n\n"
                    + "⌛️ " // эмодзи
                    + "Сессия окончена, давай по новой.";

            sendMessage
                    .setText(textToSend)
                    .row()
                    .button("Учить следующий глагол", "/learn")
                    .endRow()
                    .row()
                    .button("<< главное меню", "/start")
                    .endRow();
        } else {
            LearnSession session = sessionKeeper.get(wrapper.getSupportedMessageOrNull().getChatId());
            TestButtons testButtons = buttonsBuilder.create(session.getVerb());

            textToSend = "🎓 " // эмодзи
                    + "𝕋𝕖𝕤𝕥\n\n"
                    + "Пройдём тест!\n\n"
                    + "Выбери три формы глагола в правильном порядке:\n\n"
                    + "📌 " // эмодзи
                    + "<b>" + session.getVerb().getTranslation() + "</b>";

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
                    .button("пропустить глагол >", "/learn")
                    .endRow()
                    .row()
                    .button("<< главное меню", "/start")
                    .endRow();
        }
        return sendMessage;
    }
}
