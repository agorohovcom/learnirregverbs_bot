package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminShutdownTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        return MessageBuilder
                .create()
                .setChatId(wrapper.getSupportedMessageOrNull().getChatId())
                .setText(textBuilder())
                .row()
                .button("Выключить бота", "/admin_shutdown_confirm")
                .button("Отмена", "/admin")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }

    private String textBuilder() {
        StringBuilder result = new StringBuilder();
        result
                .append("👨‍💻 ") //эмодзи
                .append("𝔸𝕕𝕞𝕚𝕟\n\nХозяин, ты действительно хочешь выключить бота?\n\n")
                .append("Это действие невозможно отменить!");
        
        return result.toString();
    }
}
