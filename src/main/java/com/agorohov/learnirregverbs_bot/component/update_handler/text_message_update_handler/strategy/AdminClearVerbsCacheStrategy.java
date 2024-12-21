package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategyAbstractImpl;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.service.CacheService;
import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminClearVerbsCacheStrategy extends ProcessingStrategyAbstractImpl {

    private final CacheService cacheService;

    private final static String CACHE_NAME = "verbs";

    @Override
    protected MessageBuilder strategyRealization(UpdateWrapper wrapper) {
        return MessageBuilder
                .create()
                .setChatId(wrapper.getSupportedMessageOrNull().getChatId())
                .setText(clearVerbsCacheInfo())
                .row()
                .button("< в админку", "/admin")
                .endRow()
                .row()
                .button("<< главное меню", "/start")
                .endRow();
    }

    private String clearVerbsCacheInfo() {
        int before = cacheService.getCacheSize(CACHE_NAME);
        log.info("Admin is clearing verbs cache, cache size before: {}", before);
        cacheService.clearCache(CACHE_NAME);
        int after = cacheService.getCacheSize(CACHE_NAME);
        log.info("Cache cleared, cache size now: {}", after);

        StringBuilder result = new StringBuilder();
        result
                .append("👨‍💻 ") //эмодзи
                .append("𝔸𝕕𝕞𝕚𝕟\n\nОчищаю кэш глаголов, хозяин!\n\n")
                .append("Размер кэша до:\n")
                .append(before)
                .append("\n\n")
                .append("Размер кэша теперь:\n")
                .append(after);

        return result.toString();
    }
}
