package com.agorohov.learnirregverbs_bot.component.update_handler.text_update;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.AdminTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.DefaultTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.StartTextUpdateStrategy;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TextUpdate extends UpdateHandler {

    public TextUpdate(Update update, boolean isAdmin) {
        this.update = update;
        this.isAdmin = isAdmin;
        updateType = "Text";
        userId = update.getMessage().getChatId();
        choiseStrategy(update);
    }

    private void choiseStrategy(Update update) {
        switch (update.getMessage().getText()) {
            case "/start" ->
                processingStrategy = new StartTextUpdateStrategy(update);
            case "/admin" -> {
                if (isAdmin) {
                    processingStrategy = new AdminTextUpdateStrategy(update);
                } else {
                    processingStrategy = new DefaultTextUpdateStrategy(update);
                }
            }
            default ->
                processingStrategy = new DefaultTextUpdateStrategy(update);
        }
    }

//    @Override
//    public void workWithDB(){
//        User user =  new User()
//                    .setChatId(chatId)
//                    .setUserName(userName)
//                    .setLastMessageAt(lastUpdateTime);
//        try{
//            userService.save(user);
//        }catch(DataAccessException e){
//            log.error(e.getMessage());
//        }
//        
//    }
}