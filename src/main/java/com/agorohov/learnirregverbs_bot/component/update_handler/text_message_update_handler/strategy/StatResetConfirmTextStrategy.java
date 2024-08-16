package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatResetConfirmTextStrategy extends ProcessingStrategyAbstractImpl {

    private final LearningStatisticsService learningStatisticsService;

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        learningStatisticsService.deleteAllByUserChatId(wrapper.getMessage().getChatId());

        String textToSend = "📊 "    // эмодзи
                + "ℝ𝕖𝕤𝕖𝕥 𝕤𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
                + "♻️ "  // эмодзи
                + wrapper.getMessage().getChat().getUserName() + ", твоя статистика изучения "
                + "неправильных глаголов удалена.\n\n"
                + "Но ты можешь начать заново!";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Учить неправильные глаголы", "/learn")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
