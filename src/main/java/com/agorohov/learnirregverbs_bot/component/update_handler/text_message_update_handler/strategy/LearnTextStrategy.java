package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSession;
import com.agorohov.learnirregverbs_bot.component.learning.learn_session.LearnSessionKeeper;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LearnTextStrategy extends ProcessingStrategyAbstractImpl {

    private final LearnSessionKeeper sessionKeeper;
    private final VerbService verbService;

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        VerbDTO verb = getNextVerb();

        sessionKeeper.put(new LearnSession(
                wrapper.getMessage().getChatId(),
                verb,
                wrapper.getUpdateWasReceivedAt()));

        String textToSend = "𝕃𝕖𝕒𝕣𝕟\n\n"
                + wrapper.getMessage().getChat().getUserName()
                + ", запомни три формы глагола:\n\n<b>" + verb.getTranslation() + "</b>\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                + "<b>" + verb + "</b>\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                + "Когда будешь готов, нажми \"Пройти тест\"";

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

    private VerbDTO getNextVerb() {
        return verbService.getRandomVerbDTO();
    }
}
