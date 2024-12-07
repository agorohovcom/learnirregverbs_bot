package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/** Считаем:
 * 1. Сколько всего пройдено тестов
 * 2. Сколько глаголов встречено из общего числа
 * 3. Сколько глаголов изучено хорошо - 5* и 6*
 * 4. Сколько глаголов изучено средне - 3* и 4*
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class StatTextStrategy extends ProcessingStrategyAbstractImpl {

    private final LearningStatisticsService learningStatisticsService;
    private final VerbService verbService;

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        List<LearningStatisticsDTO> statistics = learningStatisticsService
                .getAllStatisticsById(wrapper.getSupportedMessageOrNull().getChatId());

        String textToSend = "";
        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getSupportedMessageOrNull().getChatId());

        if (!statistics.isEmpty()) {
            int verbsCount = verbService.getCount();
            int attemptsTotal = statistics
                    .stream()
                    .mapToInt(LearningStatisticsDTO::getAttempts)
                    .sum();
            int learnedVerbsAmount = statistics.size();
            int learnedVerbsPercent = 100 * learnedVerbsAmount / verbsCount;
            int hightRateVerbs = (int) statistics
                    .stream()
                    .filter(e -> e.getRank() >= 5)
                    .count();
            int midRateVerbs = (int) statistics
                    .stream()
                    .filter(e -> e.getRank() >= 2 && e.getRank() < 5)
                    .count();
            int hightRateVerbsPercent = 100 * hightRateVerbs / verbsCount;
            int midRateVerbsPercent = 100 * midRateVerbs / verbsCount;

            textToSend = "📊 "    // эмодзи
                + "𝕊𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                    + wrapper.getSupportedMessageOrNull().getChat().getUserName()
                    + ", вот твоя статистика:\n\n"
                    + " • Пройдено тестов: <b>" + attemptsTotal + "</b>\n"
                    + " • Встречено глаголов: <b>" + learnedVerbsAmount + " (" + learnedVerbsPercent + " %)</b>\n"
                    + " • Хорошо изучено: <b>" + hightRateVerbs + " (" + hightRateVerbsPercent + " %)</b>\n"
                    + " • Средне изучено: <b>" + midRateVerbs + " (" + midRateVerbsPercent + " %)</b>\n\n"
                    + "Успехов тебе в изучении!";

            sendMessage.setText(textToSend)
                    .row()
                    .button("Обнулить статистику", "/stat_reset")
                    .endRow()
                    .row()
                    .button("<< главное меню", "/start")
                    .endRow();
        } else {
            textToSend = "📊 "    // эмодзи
                + "𝕊𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                    + wrapper.getSupportedMessageOrNull().getChat().getUserName() + ", твоя статистика пока что пуста.\n\n"
                    + "Изучай неправильные глаголы, и с каждым ответом твоя статистика будет изменяться.";

            sendMessage.setText(textToSend)
                    .row()
                    .button("Учить неправильные глаголы", "/learn")
                    .endRow()
                    .row()
                    .button("<< главное меню", "/start")
                    .endRow();
        }

        log.info("User (id = {}) requested his statistics", wrapper.getSupportedMessageOrNull().getChatId());
        return sendMessage;
    }
}
