package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSession;
import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSessionKeeper;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LearnTextStrategy extends ProcessingStrategyAbstractImpl {

    private final LearnSessionKeeper sessionKeeper;
    private final LearningStatisticsService statisticsService;

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        LearnSession session = sessionKeeper.isExists(wrapper.getMessage().getChatId()) && sessionKeeper.hasNextVerb(wrapper.getMessage().getChatId())
                ? sessionKeeper.get(wrapper.getMessage().getChatId())
                : sessionKeeper.createAndPutAndGet(wrapper.getMessage().getChatId());
        
        VerbDTO verb = session.getNextVerb();
        short starsAmount = statisticsService.existByUserChatIdAndVerbId(wrapper.getMessage().getChatId(), verb.getId())
                ? statisticsService.findByUserChatIdAndVerbId(wrapper.getMessage().getChatId(), verb.getId()).getRank()
                : 0;
        
        String startText = "";
        String verbText = "";
        
        if (starsAmount < 3) {
            startText = "🎓 " // эмодзи
                + "𝕃𝕖𝕒𝕣𝕟\n\n"
                + wrapper.getMessage().getChat().getUserName()
                + ", запомни три формы глагола:\n\n";
            verbText = "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                + "📖 " // эмодзи
                + "<b>" + verb + "</b>\n"
                + "📌️ " // эмодзи
                + "(" + verb.getTranslation() + ")\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n";
        } else if (starsAmount >= 3 && starsAmount < 5) {
            startText = "🎓 " // эмодзи
                + "𝕃𝕖𝕒𝕣𝕟\n\n"
                + wrapper.getMessage().getChat().getUserName()
                + ", вспомни три формы глагола:\n\n";
            verbText = "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                + "📖 " // эмодзи
                + "<b>" + verb.getInfinitive() + " / ...</b>\n"
                + "📌️ " // эмодзи
                + "(" + verb.getTranslation() + ")\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n";
        } else {
            startText = "🎓 " // эмодзи
                + "𝕃𝕖𝕒𝕣𝕟\n\n"
                + wrapper.getMessage().getChat().getUserName()
                + ", вспомни все 3 формы глагола:\n\n";
            verbText = "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
//                + "📖 " // эмодзи
//                + "<b>" + verb.getInfinitive() + " / ...</b>\n"
                + "📌️ " // эмодзи
                + "(" + verb.getTranslation() + ")\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n";
        }
            
        String textToSend = startText + verbText
                + "🏆  " // эмодзи
                + session.getStarsString(starsAmount) + "\n\n"
                + "Если готов, жми \"Пройти тест\"";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Пройти тест", "/learn_test")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
