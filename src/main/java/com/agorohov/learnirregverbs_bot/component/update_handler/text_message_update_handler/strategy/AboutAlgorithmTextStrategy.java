package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AboutAlgorithmTextStrategy extends ProcessingStrategyAbstractImpl {

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
                + "  Каждое прохождение теста влияет на статистику /stat - у глагола меняется ранг.\n\n"
                + "  Во время теста тебе нужно выбрать 3 формы неправильного глагола в пральньном порядке, нажав на 3 кнопки. "
                + "Обрати внимание, в тесте может быть лишняя кнопка с одним из правильных ответов, "
                + "нажатие на которую может привести к провалу теста.\n\n"
                + "  Если тест пройден, то общее число попыток, серия правильных ответов и ранг глагола "
                + "увеличиваются на 1. При неправильном ответе число попыток увеличивается на 1, ранг "
                + "уменьшается на 1, серия правильных ответов становится 0.\n\n"
                + "  Ранг может быть от 0 до 5, но если серия правильных ответов 10 или больше - ранг "
                + "становится 6.\n\n"
                + "  При ранге 0-1 перед тестом ты видишь все 3 формы глагола и перевод. "
                + "При ранге 2-4 ты видишь только первую форму глагола и перевод. "
                + "При ранге 5-6 ты видишь только перевод.";
//                + "  [опция в  разработке"
//                + " 🛠]\n"   // эмодзи
//                + "  Чем ниже ранг глагола, тем выше твой шанс встретить его снова. Таким образом, "
//                + "при систематическом обучении тебе будут чаще попадаться те глаголы, "
//                + "которые ты знаешь хуже.\n\n";

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
