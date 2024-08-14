package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSession;
import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSessionKeeper;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LearnTestResultTextStrategy extends ProcessingStrategyAbstractImpl {

    private final LearningStatisticsService learningStatisticsService;
    private final LearnSessionKeeper sessionKeeper;
    private final Random random;

    private String[] congrats = new String[]{
        "Верно!",
        "Правильно!",
        "Это правильный ответ!",
        "Молодец!",
        "Супер!",
        "Так держать!",
        "Ты не перестаешь удивлять!",
        "Да!"
    };

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "";

        boolean isSessionExist = sessionKeeper.isExist(wrapper.getMessage().getChatId());

        if (!isSessionExist) {
            textToSend = "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                    + "Время вышло, выбери другое слово.";
        } else {
            LearnSession session = sessionKeeper.get(wrapper.getMessage().getChatId());
            session.saveAnswer(wrapper.getUpdate().getCallbackQuery().getData());
            sessionKeeper.put(session);

            LearningStatisticsDTO learningStatistics = null;

            if (learningStatisticsService.existByUserChatIdAndVerbId(wrapper.getMessage().getChatId(), session.getVerb().getId())) {
                learningStatistics = learningStatisticsService.getByUserChatIdAndVerbId(wrapper.getMessage().getChatId(), session.getVerb().getId());
            } else {
                learningStatistics = new LearningStatisticsDTO()
                        .setVerb(session.getVerb())
                        .setUser(wrapper.giveMeUserDTO());
            }

            if (!session.isAllAnswersReceived()) {
                wrapper.setExecutable(false);
            } else {
                if (session.isCorrectResult()) {
                    learningStatistics.wins();

                    textToSend = "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                            + "✅ " + congrats[random.nextInt(congrats.length)] + "\n\n"
                            + "<b>" + session.getVerb() + "</b>\n"
                            + "(" + session.getVerb().getTranslation() + ")\n\n"
                            + "Результат записан. Продолжим?";
                } else {
                    learningStatistics.loses();

                    textToSend = "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                            + "❌ К сожалению, ответ неверный.\n\n"
                            + "Твой ответ:\n"
                            + "<b>" + session.getAnswers()[0] + " / " + session.getAnswers()[1] + " / " + session.getAnswers()[2] + "</b>\n\n"
                            + "Правильный ответ:\n"
                            + "<b>" + session.getVerb() + "</b>\n"
                            + "(" + session.getVerb().getTranslation() + ")\n\n"
                            + "Результат записан. Продолжим?";
                }

                learningStatisticsService.save(learningStatistics);
            }
        }

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Учить следующее слово", "/learn")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}