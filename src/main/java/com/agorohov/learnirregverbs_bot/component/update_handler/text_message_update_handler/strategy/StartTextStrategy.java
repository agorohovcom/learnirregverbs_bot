package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;

@Component
@RequiredArgsConstructor
public class StartTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "🇬🇧 " // эмодзи
                + "𝕃𝕖𝕒𝕣𝕟 𝕀𝕣𝕣𝕖𝕘𝕦𝕝𝕒𝕣 𝕍𝕖𝕣𝕓𝕤 𝔹𝕠𝕥\n\n"
                + "Привет, " + wrapper.getMessage().getChat().getUserName() + "!\n\n"
                + "🤖 " // эмодзи
                + "Это бот для изучения неправильных глаголов английского языка.\n\n"
                + "Тут ты можешь:\n\n"
                + "  • учить неправильные глаголы,\n"
                + "  • отслеживать свой прогресс,\n"
                + "  • узнать больше об этом боте и\n"
                + "  • рассказать о нём друзьям!"
                + " 😉"  // эмодзи
                + "\n\n"
                + "🤓 "  // эмодзи
                + "Ну что, давай начнём учиться!";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("Учить неправильные глаголы", "/learn")
                .endRow()
                .row()
                .button("Статистика", "/stat")
                .endRow()
                .row()
                .button("О боте", "/about")
                .endRow()
                .row()
                .button("Помощь", "/help")
                .endRow();
    }
}
