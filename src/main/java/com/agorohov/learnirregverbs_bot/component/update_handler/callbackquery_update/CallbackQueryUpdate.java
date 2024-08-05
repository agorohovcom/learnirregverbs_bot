package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy.DismissCallbackQueryUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy.FailCallbackQueryUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.TextUpdate;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.StartTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.exception.FailCallbackQueryException;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class CallbackQueryUpdate extends UpdateHandler {
    
    public final String updateType = "CallbackQuery";
    
    public CallbackQueryUpdate(Update update, String botOwner) {
        updateHandlerFieldsInitializer(update, updateType, botOwner);
        choiseStrategy();
    }

    private void choiseStrategy() {
        switch (msgCallbackData) {
            case "dismiss" ->
                processingStrategy = new DismissCallbackQueryUpdateStrategy(this);
            case "/start" -> 
                processingStrategy = new StartTextUpdateStrategy(this);
            default ->
                processingStrategy = new FailCallbackQueryUpdateStrategy(this);
        }
    }
}
