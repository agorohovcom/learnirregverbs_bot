package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.component.update_handler.text_update_strategy.DefaultTextUpdateStrategy;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update_strategy.StartTextUpdateStrategy;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
//@Setter
//@Accessors(chain = true)
public class TextUpdate extends UpdateHandler {

    public TextUpdate(Update update) {
        this.update = update;
        updateType = "Text";
        choiseStrategy(update);
    }

    private void choiseStrategy(Update update) {
        switch (update.getMessage().getText()) {
            case "/start" ->
                processingStrategy = new StartTextUpdateStrategy(update);
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
