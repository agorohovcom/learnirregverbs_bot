package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.service.UserService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminTextStrategy extends ProcessingStrategyAbstractImpl {
    
    private final UserService userService;

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        return MessageBuilder
                .create()
                .setChatId(wrapper.getMessage().getChatId())
                .setText(textBuilder(wrapper))
                .row()
                .button("Вызвать GC", "/admin_call_gc")
                .endRow()
                .row()
                .button("Выключить бота", "/admin_shutdown")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }
    
    private String textBuilder(UpdateWrapper wrapper) {
        StringBuilder result = new StringBuilder();
        result
                .append("👨‍💻 ")  //эмодзи
                .append("𝔸𝕕𝕞𝕚𝕟\n\nПриветствую, хозяин!\n\n")
                .append("Бот работает уже:\n")
                .append(workingTime(wrapper.getBotStartsAt()))
                .append("\n\n")
                .append("Всего пользователей:\n")
                .append(userService.getCount())
                .append("\n\n")
                .append("Приложение использует памяти:\n")
                .append(getUsedMB())
                .append(" Мб\n\n");
        
        
        return result.toString();
    }
    
    private String workingTime(long start) {
        long tms = (System.currentTimeMillis() - start) / 1000;
        long days = tms / 86400;
        long hours = (tms - days * 86400) / 3600;
        long minutes = (tms - days * 86400 - hours * 3600) / 60;
        long seconds = tms - days * 86400 - hours * 3600 - minutes * 60;

        return String.format("%d д. : %d ч. : %d мин. : %d сек.",
                days, hours, minutes, seconds);
    }
    
    private int getUsedMB() {
        // считаем используемую память и переводим в МБ
        return (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
    }
}
