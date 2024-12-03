package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AboutTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        String textToSend = "ℹ️ " // эмодзи
                + "𝔸𝕓𝕠𝕦𝕥\n\n"
                + "👨‍💻 " // эмодзи
                + "<b>От автора:</b>\n\n"
                + "  Привет! Меня зовут Александр Горохов. Этот бот - мой пет-проект на Java"
                + " ☕" // эмодзи
                + "️.\n\n"
                + "  Цель создания бота - разобраться в используемых технологиях и "
                + "прикрепить проект к резюме. И выучить неправильные глаголы, конечно 😀\n\n"
                + "  Если тебе понравился бот или есть замечания / предложения, "
                + "ищи меня по приложенным ссылкам. И покажи моего бота друзьям! 🤝\n\n"
                + "  Спасибо, что заглянул 😉\n\n"
                + "🔗 " // эмодзи
                + "<b>Ссылки:</b>\n\n"
                + "  • <a href=\"https://t.me/Sanchio\">Telegram</a>\n"
                + "  • <a href=\"https://www.linkedin.com/in/aleksandr-gorohov/\">Linkedin</a>\n"
                + "  • <a href=\"https://github.com/agorohovcom/\">Github</a>\n"
                + "  • <a href=\"https://agorohov.com/\">Блог</a>\n\n";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getSupportedMessageOrNull().getChatId())
                .setText(textToSend)
                .row()
                .button("Алгоритм работы бота", "/about_algorithm")
                .endRow()
                .row()
                .button("Используемые технологии", "/about_technologies")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
