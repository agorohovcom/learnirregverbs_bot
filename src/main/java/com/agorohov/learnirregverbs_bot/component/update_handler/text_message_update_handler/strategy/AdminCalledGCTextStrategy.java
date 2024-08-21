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
public class AdminCalledGCTextStrategy extends ProcessingStrategyAbstractImpl {

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(callGCInfo())
                .row()
                .button("< в админку", "/admin")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }

    private int getUsedMB() {
        // считаем используемую память и переводим в МБ
        return (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
    }

    private String callGCInfo() {
        int before = getUsedMB();
        log.info("Admin is calling GC, used memory before: " + before + " MB");
        System.gc();
        int after = getUsedMB();
        log.info("GC called, used memory after: " + after + " MB");

        StringBuilder result = new StringBuilder();
        result
                .append("👨‍💻 ") //эмодзи
                .append("𝔸𝕕𝕞𝕚𝕟\n\nПризываю GC, хозяин!\n\n")
                .append("Использовано памяти до:\n")
                .append(before)
                .append(" Мб\n\n")
                .append("Использовано памяти после:\n")
                .append(after)
                .append(" Мб.");
        
        return result.toString();
    }
}
