package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.learning.LearnSession;
import com.agorohov.learnirregverbs_bot.component.learning.LearnSessionKeeper;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Component
@RequiredArgsConstructor
public class LearnTextStrategy implements ProcessingStrategy {

    private final LearnSessionKeeper sessionKeeper;
    private final VerbService verbService;

    @Override
    public BotApiMethod processUpdate(UpdateWrapper wrapper) {
        wrapper.setStrategy(this.getClass().getSimpleName());

//        List<VerbDTO> vdtos = Stream.generate(
//                () -> verbService.getRandomVerbDTO())
//                .distinct()
//                .limit(3)
//                .collect(Collectors.toCollection(ArrayList::new));
        VerbDTO verb = verbService.getRandomVerbDTO();

        sessionKeeper.put(new LearnSession(
                wrapper.getMessage().getFrom().getId(),
                verb,
                wrapper.getUpdateWasReceivedAt()));

        String textToSend = "𝕃𝕖𝕒𝕣𝕟\n\n"
                + wrapper.getMessage().getChat().getUserName() + ", давай учиться!\n"
                + "Постарайся запомнить три формы глагола.\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                + verb + "\n\n"
                + "- - - - - - - - - - - - - - - - - - - - - - - - -\n\n"
                + "Когда будешь готов, нажми \"Пройти тест\"";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Пройти тест", "/learn_test")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();

        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
