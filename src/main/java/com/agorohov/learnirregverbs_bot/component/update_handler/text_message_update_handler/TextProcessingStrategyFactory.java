package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.config.BotConfig;

@Component
@RequiredArgsConstructor
public class TextProcessingStrategyFactory {
    
    private final BotConfig config;

    private final StartTextStrategy startTextStrategy;
    private final LearnTextStrategy learnTextStrategy;
    private final StatTextStrategy statTextStrategy;
    private final AboutTextStrategy aboutTextStrategy;
    private final HelpTextStrategy helpTextStrategy;
    private final AdminTextStrategy adminTextStrategy;
    private final DefaultTextUpdateStrategy defaultTextUpdateStrategy;

    public ProcessingStrategy getStrategy(Update update) {
        return switch (update.getMessage().getText()) {
            case "/start" -> startTextStrategy;
            case "/learn" -> learnTextStrategy;
            case "/stat" -> statTextStrategy;
            case "/about" -> aboutTextStrategy;
            case "/help" -> helpTextStrategy;
            case "admin" -> config.getBotOwner()
                    .equals(update.getMessage().getFrom().getId().toString())
                ? adminTextStrategy
                : defaultTextUpdateStrategy;
            default -> defaultTextUpdateStrategy;
        };
    }
}
