package com.agorohov.learnirregverbs_bot.component.update_handler.text_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.*;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class TextUpdate extends UpdateHandler {
    
    protected String userName;
    protected String userFirstName;
    protected String msgBody;

    public TextUpdate(Update update, boolean isAdmin) {
        updateHundlerFieldInitializer(update, isAdmin);
        this.updateType = "Text";
        choiseStrategy(update);
        
    }

    private void choiseStrategy(Update update) {
        switch (msgBody) {
            case "/start" ->
                processingStrategy = new StartTextUpdateStrategy(update);
            case "/admin" -> {
                if (isAdmin) {
                    processingStrategy = new AdminTextUpdateStrategy(update);
                } else {
                    processingStrategy = new DefaultTextUpdateStrategy(update);
                }
            }
            case "/buttons_test" ->
                processingStrategy = new ButtonsTestTextUpdateStrategy(update);
            default ->
                processingStrategy = new DefaultTextUpdateStrategy(update);
        }
    }

    @Override
    protected void updateHundlerFieldInitializer(Update update, boolean isAdmin) {

        this.userId = update.getMessage().getChatId();
        this.userName = update.getMessage().getChat().getUserName();
        this.userFirstName = update.getMessage().getChat().getFirstName();
        this.msgId = update.getMessage().getMessageId();
        this.msgBody = update.getMessage().getText();

        this.isAdmin = isAdmin;
    }
}
