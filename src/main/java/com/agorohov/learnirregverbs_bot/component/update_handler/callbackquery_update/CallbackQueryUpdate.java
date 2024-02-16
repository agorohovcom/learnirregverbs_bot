package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy.DismissCallbackQueryUpdateStrategy;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class CallbackQueryUpdate extends UpdateHandler {
    
    protected String callBaclQueryData;
    
    public CallbackQueryUpdate(Update update, boolean isAdmin) {
        updateHundlerFieldInitializer(update, isAdmin);
        this.updateType = "CallbackQuery";
        choiseStrategy(update);
    }

    private void choiseStrategy(Update update) {
        switch (callBaclQueryData) {
            // отрицательный ответ
            case "dismiss" ->
                processingStrategy = new DismissCallbackQueryUpdateStrategy(update);
        }
    }

    @Override
    protected void updateHundlerFieldInitializer(Update update, boolean isAdmin) {
        this.callBaclQueryData = update.getCallbackQuery().getData();
        this.msgId = update.getCallbackQuery().getMessage().getMessageId();
        this.userId = update.getCallbackQuery().getMessage().getChatId();

        this.isAdmin = isAdmin;
    }
}
