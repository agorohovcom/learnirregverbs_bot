package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@Slf4j
@RequiredArgsConstructor
public class StatTextStrategy implements ProcessingStrategy {

    private final LearningStatisticsService learningStatisticsService;
    private final VerbService verbService;

    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        wrapper.setStrategy(this.getClass().getSimpleName());

        List<LearningStatisticsDTO> statistics = learningStatisticsService
                .getAllStatisticsById(wrapper.getMessage().getChatId());

        String textToSend = "";
        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId());

        if (!statistics.isEmpty()) {
            int verbsCount = verbService.getCount();
            int appemptsTotal = statistics
                    .stream()
                    .mapToInt(e -> e.getAttempts())
                    .sum();
            int learnedVerbsAmount = statistics.size();
            int learnedVerbsPercent = 100 * learnedVerbsAmount / verbsCount;
            int hightRateVerbs = (int) statistics
                    .stream()
                    .filter(e -> e.getRank() >= 5)
                    .count();
            int midRateVerbs = (int) statistics
                    .stream()
                    .filter(e -> e.getRank() >= 3 && e.getRank() < 5)
                    .count();
            int hightRateVerbsPercent = 100 * hightRateVerbs / verbsCount;
            int midRateVerbsPercent = 100 * midRateVerbs / verbsCount;

            textToSend = "𝕊𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                    + wrapper.getMessage().getChat().getUserName()
                    + ", вот твоя статистика:\n\n"
                    + " • Пройдено тестов: <b>" + appemptsTotal + "</b>\n"
                    + " • Встречено глаголов: <b>" + learnedVerbsAmount + " (" + learnedVerbsPercent + " %)</b>\n"
                    + " • Хорошо изучено: <b>" + hightRateVerbs + " (" + hightRateVerbsPercent + " %)</b>\n"
                    + " • Средне изучено: <b>" + midRateVerbs + " (" + midRateVerbsPercent + " %)</b>\n\n"
                    + "Успехов тебе в изучении!";

            sendMessage.setText(textToSend)
                    .row()
                    .button("Обнулить статистику", "/stat_reset")
                    .endRow()
                    .row()
                    .button("Учить неправильные глаголы", "/learn")
                    .endRow()
                    .row()
                    .button("<< главное меню", "/start")
                    .endRow();
        } else {
            textToSend = "𝕊𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                    + wrapper.getMessage().getChat().getUserName() + ", твоя статистика пока что пуста.\n\n"
                    + "Изучай неправильные глаголы, и с каждым ответом твоя статистика будет изменяться.";

            sendMessage.setText(textToSend)
                    .row()
                    .button("Учить неправильные глаголы", "/learn")
                    .endRow()
                    .row()
                    .button("<< главное меню", "/start")
                    .endRow();
        }

        log.info("The user (id = " + wrapper.getMessage().getChatId() + ") requested his statistics");
        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
