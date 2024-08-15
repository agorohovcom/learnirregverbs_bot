package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSessionKeeper;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LearnTextStrategy extends ProcessingStrategyAbstractImpl {

    private final LearnSessionKeeper sessionKeeper;

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {

        // запрашиваем следущий глагол у существующей сессии или 
        // создаём новую и запрашиваем у неё
        VerbDTO verb = sessionKeeper.getOrCreateAndPutAndGet(wrapper.getMessage().getChatId()).getNextVerb();

        String textToSend = "🎓 " // эмодзи
                + "𝕃𝕖𝕒𝕣𝕟\n\n"
                + wrapper.getMessage().getChat().getUserName()
                + ", запомни три формы глагола:\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                + "📖 " // эмодзи
                + "<b>" + verb + "</b>\n"
                + "📌️ " // эмодзи
                + "(" + verb.getTranslation() + ")\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
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
