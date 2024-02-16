package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy.DismissCallbackQueryUpdateStrategy;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryUpdate extends UpdateHandler {

    public CallbackQueryUpdate(Update update, boolean isAdmin) {
        this.update = update;
        this.isAdmin = isAdmin;
        updateType = "CallbackQuery";
        choiseStrategy(update);
    }
    
    private void choiseStrategy(Update update){
        switch (update.getCallbackQuery().getData()){
            // отрицательный ответ
            case "dismiss" -> processingStrategy = new DismissCallbackQueryUpdateStrategy(update);
        }
    }
}
