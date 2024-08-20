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
                + "🧩 " // эмодзи
                + "<b>Алгоритм работы бота:</b>\n\n"
                + "  Переходя в раздел /learn, ты открываешь учебную сессию и "
                + "получаешь несколько случайных глаголов. "
                + "Каждый из них попадётся тебе несколько раз, после чего "
                + "открывается новая учебная сессия.\n\n"
                + "  Каждое прохождение теста влияет на твою статистику /stat.\n\n"
                + "  Если тест пройден, то общее число попыток, серия правильных ответов и ранг глагола "
                + "увеличиваются на 1. При неправильном ответе число попыток увеличивается на 1, ранг "
                + "уменьшается на 1, серия правильных ответов становится 0.\n\n"
                + "  Ранг может быть от 0 до 5, но если серия правильных ответов 10 или больше - ранг "
                + "становится 6.\n\n"
                + "  При ранге 0-2 перед тестом ты видишь все 3 формы глагола и перевод. "
                + "При ранге 3-4 ты видишь только первую форму глагола и перевод. "
                + "При ранге 5-6 ты видишь только перевод.\n\n"
                + "  [Опция в  разработке]\n"
                + "  Чем ниже ранг глагола, тем выше твой шанс встретить его снова. Таким образом, "
                + "при систематическом обучении тебе будут чаще попадаться те глаголы, "
                + "которые ты знаешь хуже.\n\n"
                + "⚙️ " // эмодзи
                + "<b>Используемые технологии:</b>\n\n"
                + "  • Java SE\n"
                + "  • String Boot\n"
                + "  • Telegram Bots Spring Boot Starter\n"
                + "  • H2 Database\n"
                + "  • Liquibase\n"
                + "  • Docker\n\n"
                + "🔗 " // эмодзи
                + "<b>Ссылки:</b>\n\n"
                + "  • <a href=\"https://github.com/agorohovcom/learnirregverbs_bot/\">Github проекта</a>\n"
                + "  • <a href=\"https://www.linkedin.com/in/aleksandr-gorohov/\">Linkedin</a>\n"
                + "  • <a href=\"https://agorohov.com/\">Сайт автора</a>\n\n"
                + "👨‍💻 " // эмодзи
                + "<b>От автора:</b>\n\n"
                + "  Привет! Меня зовут Александр Горохов. Этот бот - мой первый пет-проект на Java.\n\n"
                + "  У меня много идей по развитию бота, добавлению режимов, усложнению алгоритмов. "
                + "Но я ничего не обещаю, ведь основная цель - это разобраться в используемых технологиях и "
                + "прикрепить проект к резюме. И выучить неправильные глаголы, конечно 😀\n\n"
                + "  Если тебе понравился бот или есть замечания / предложения, "
                + "ищи меня по приложенным ссылкам. И покажи моего бота друзьям! 🤝\n\n"
                + "  Спасибо, что заглянул 😉";

        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textToSend)
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
}
