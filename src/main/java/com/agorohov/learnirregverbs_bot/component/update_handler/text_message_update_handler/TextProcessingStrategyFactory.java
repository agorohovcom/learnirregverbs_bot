package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextProcessingStrategyFactory {

    private final StartTextStrategy startTextStrategy;
    private final LearnTextStrategy learnTextStrategy;
    private final StatTextStrategy statTextStrategy;
    private final StatResetTextStrategy statResetTextStrategy;
    private final AboutTextStrategy aboutTextStrategy;
    private final HelpTextStrategy helpTextStrategy;
    private final AdminTextStrategy adminTextStrategy;
    private final DefaultTextUpdateStrategy defaultTextUpdateStrategy;

    public ProcessingStrategy getStrategy(UpdateWrapper wrapper) {
        return switch (wrapper.getSupportedMessageOrNull().getText()) {
            case "/start" -> startTextStrategy;
            case "/learn" -> learnTextStrategy;
            case "/stat" -> statTextStrategy;
            case "/stat_reset" -> statResetTextStrategy;
            case "/about" -> aboutTextStrategy;
            case "/help" -> helpTextStrategy;
            case "/admin" -> wrapper.isAdmin()
                ? adminTextStrategy
                : defaultTextUpdateStrategy;
            default -> defaultTextUpdateStrategy;
        };
    }
}
