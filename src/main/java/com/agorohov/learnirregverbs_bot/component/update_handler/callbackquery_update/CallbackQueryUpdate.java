package com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy.DismissCallbackQueryUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.strategy.FailCallbackQueryUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.AboutTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.HelpTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.LearnTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.StartTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.StatTextUpdateStrategy;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class CallbackQueryUpdate extends UpdateHandler {

    public final String updateType = "CallbackQuery";

    public CallbackQueryUpdate(Update update, String botToken, String botOwner) {
        updateHandlerFieldsInitializer(update, updateType, botToken, botOwner);
        choiseStrategy();
    }

    private void choiseStrategy() {
        switch (msgCallbackData) {
            case "/start" ->
                processingStrategy = new StartTextUpdateStrategy(this);
            case "/learn" ->
                processingStrategy = new LearnTextUpdateStrategy(this);
            case "/stat" ->
                processingStrategy = new StatTextUpdateStrategy(this);
            case "/about" ->
                processingStrategy = new AboutTextUpdateStrategy(this);
            case "/help" ->
                processingStrategy = new HelpTextUpdateStrategy(this);
            case "dismiss" ->
                processingStrategy = new DismissCallbackQueryUpdateStrategy(this);
            default ->
                processingStrategy = new FailCallbackQueryUpdateStrategy(this);
        }
    }
}
