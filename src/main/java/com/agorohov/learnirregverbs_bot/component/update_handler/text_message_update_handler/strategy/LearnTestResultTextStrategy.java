package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSession;
import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSessionKeeper;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.exception.UserNotFoundException;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class LearnTestResultTextStrategy extends ProcessingStrategyAbstractImpl {

    private final LearningStatisticsService learningStatisticsService;
    private final LearnSessionKeeper sessionKeeper;
    private final Random random;

    private final String[] congrats = new String[]{
        "👍 Верно!", // тут эмодзи
        "Правильно!",
        "Это правильный ответ!",
        "Молодец!",
        "🥳 Супер!", // тут эмодзи
        "Так держать!",
        "Ты не перестаешь удивлять!",
        "🎉 Да!" // тут эмодзи
    };

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "";

        if (!sessionKeeper.isExists(wrapper.getSupportedMessageOrNull().getChatId())) {
            textToSend = "🎓 " // эмодзи
                    + "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                    + "⌛️ " // эмодзи
                    + "Текущая сессия окончена, друг.";
        } else {
            LearnSession session = sessionKeeper.get(wrapper.getSupportedMessageOrNull().getChatId());
            session.saveAnswer(wrapper.getUpdate().getCallbackQuery().getData());

            VerbDTO verb = session.getVerb();

            // получаем LearningStatisticsDTO из session, БД или создаём новый
            LearningStatisticsDTO learningStatistics = null;
            if (session.getLearningStatisticsOrNull() != null) {
                learningStatistics = session.getLearningStatisticsOrNull();
            } else {
                learningStatistics = learningStatisticsService.existByUserChatIdAndVerbId(
                        wrapper.getSupportedMessageOrNull().getChatId(), verb.getId())
                        ? learningStatisticsService.findByUserChatIdAndVerbId(
                        wrapper.getSupportedMessageOrNull().getChatId(), verb.getId())
                        : new LearningStatisticsDTO()
                                .setVerb(verb)
                        .setUser(wrapper.giveMeUserDTO()
                                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден")));
            }

            // если 3 ответа ещё не выбрано, не выполнять execute()
            if (!session.isThreeAnswersReceived()) {
                wrapper.setExecutable(false);
            } else {
                if (session.isCorrectResult()) {
                    learningStatisticsService.saveWin(learningStatistics);

                    textToSend = "✅ " // эмодзи
                            + "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                            + congrats[random.nextInt(congrats.length)] + "\n\n"
                            //                            + "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                            + "<b>" + verb + "</b>\n"
                            + "(" + verb.getTranslation() + ")\n\n"
                            + "- - - - - - - - - - - - - - - - - - - - - - - - -\n"
                            + "🏆 " // эмодзи
                            + session.getStarsString(learningStatistics.getRank()) + "\n\n"
                            + "Результат сохранён. Продолжим?";
                } else {
                    learningStatisticsService.saveLose(learningStatistics);

                    textToSend = "❌ "
                            + "𝕋𝕖𝕤𝕥 𝕣𝕖𝕤𝕦𝕝𝕥\n\n"
                            + "К сожалению, ответ неверный.\n\n"
                            + "✖️ " // эмодзи
                            + "Твой ответ:\n\n"
                            + "<b>" + session.getAnswer(0) + " / "
                            + session.getAnswer(1) + " / "
                            + session.getAnswer(2) + "</b>\n\n"
                            + "✔️ " // эмодзи
                            + "Правильный ответ:\n\n"
                            // + "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                            + "<b>" + verb + "</b>\n"
                            + "(" + verb.getTranslation() + ")\n\n"
                            + "- - - - - - - - - - - - - - - - - - - - - - - - -\n"
                            + "🏆 " // эмодзи
                            + session.getStarsString(learningStatistics.getRank()) + "\n\n"
                            + "Результат записан. Продолжим?";
                }
            }
        }

        return MessageBuilder
                .create()
                .setChatId(wrapper.getSupportedMessageOrNull().getChatId())
                .setText(textToSend)
                .row()
                .button("Учить следующий глагол", "/learn")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
