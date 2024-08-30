package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AboutUsedTechnologiesTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "ℹ️ " // эмодзи
                + "𝔸𝕓𝕠𝕦𝕥\n\n"
                + "⚙️ " // эмодзи
                + "<b>Используемые технологии:</b>\n\n"
                + "  • Java SE\n"
                + "  • String Boot\n"
                + "  • Telegram Bots Spring Boot Starter\n"
                + "  • H2 Database\n"
                + "  • Liquibase\n"
                + "  • Docker";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("< о боте", "/about")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
