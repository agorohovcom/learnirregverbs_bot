package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;

@Component
@RequiredArgsConstructor
public class TextProcessingStrategyFactory {

    private final StartTextStrategy startTextStrategy;
    private final LearnTextStrategy learnTextStrategy;
    private final StatTextStrategy statTextStrategy;
    private final AboutTextStrategy aboutTextStrategy;
    private final HelpTextStrategy helpTextStrategy;
    private final DefaultTextUpdateStrategy defaultTextUpdateStrategy;

    public ProcessingStrategy getStrategy(Update update) {
        return switch (update.getMessage().getText()) {
            case "/start" -> startTextStrategy;
            case "/learn" -> learnTextStrategy;
            case "/stat" -> statTextStrategy;
            case "/about" -> aboutTextStrategy;
            case "/help" -> helpTextStrategy;
            default -> defaultTextUpdateStrategy;
        };
    }
}
