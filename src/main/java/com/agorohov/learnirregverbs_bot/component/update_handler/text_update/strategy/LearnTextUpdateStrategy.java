package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;

import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
import com.agorohov.learnirregverbs_bot.component.learning.LearningWithTestSession;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import com.agorohov.learnirregverbs_bot.service.implementation.VerbServiceImpl;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@RequiredArgsConstructor
public class LearnTextUpdateStrategy implements UpdateProcessingStrategy {

    private final UpdateHandler uh;
//    private VerbService verbService;
    
//    @Autowired
//    public void setVerbService(VerbService verbService) {
//        this.verbService = verbService;
//    }

    @Override
    public BotApiMethod processUpdate() {
//        VerbDTO randomVerb = getRandomIrrVerbDTO();

        String textToSend = "𝕃𝕖𝕒𝕣𝕟\n\n"
                + uh.getUserFirstName() + ", начнём учиться!\n\n"
                + "Перед тобой 5 случайных неправильных глаголов в трёх формах "
                + "и с переводом. Постарайся их запомнить, чтобы пройти тест.\n\n\n"
//                + randomVerb.toString()
                + "\n\n\n"
                + "Когда будешь готов, нажми \"Пройти тест\"";

        var sendMessage = MessageBuilder
                .create()
                .setChatId(uh.getUserId())
                .setText(textToSend)
                .row()
                .button("Пройти тест", "/learn_test")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();

        return updateOrCreateMessage(uh, sendMessage);
    }

//    private VerbDTO getRandomIrrVerbDTO() {
//        return verbService.findById(new Random().nextInt(verbService.getCount()));
//    }
}
