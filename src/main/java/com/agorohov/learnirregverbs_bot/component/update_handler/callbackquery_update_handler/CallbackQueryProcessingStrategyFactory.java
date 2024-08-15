package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update_handler.strategy.*;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler.strategy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.agorohov.learnirregverbs_bot.component.update_handler.ProcessingStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;

@Component
@RequiredArgsConstructor
public class CallbackQueryProcessingStrategyFactory {
   
    private final DismissCallbackQueryStrategy dismissCallbackQueryStrategy;
    private final FailCallbackQueryStrategy failCallbackQueryStrategy;
    
    private final StartTextStrategy startTextStrategy;
    private final LearnTextStrategy learnTextStrategy;
    private final LearnTestTextStrategy learnTestTextStrategy;
    private final LearnTestResultTextStrategy learnTestResultTextStrategy;
    private final StatTextStrategy statTextStrategy;
    private final StatResetTextStrategy statResetTextStrategy;
    private final StatResetConfirmTextStrategy statResetConfirmTextStrategy;
    private final AboutTextStrategy aboutTextStrategy;
    private final HelpTextStrategy helpTextStrategy;
    
    public ProcessingStrategy getStrategy(UpdateWrapper wrapper) {
        String data = wrapper.getUpdate().getCallbackQuery().getData();
        
        if(data.startsWith("/learn_test_fail_") || data.startsWith("/learn_test_ok_")){
            return learnTestResultTextStrategy;
        }
        
        return switch (data) {
            case "/dismiss" -> dismissCallbackQueryStrategy;
            case "/start" -> startTextStrategy;
            case "/learn" -> learnTextStrategy;
            case "/learn_test" -> learnTestTextStrategy;
            case "/stat" -> statTextStrategy;
            case "/stat_reset" -> statResetTextStrategy;
            case "/stat_reset_confirm" -> statResetConfirmTextStrategy;
            case "/about" -> aboutTextStrategy;
            case "/help" -> helpTextStrategy;
            default -> failCallbackQueryStrategy;
        };
    }
}