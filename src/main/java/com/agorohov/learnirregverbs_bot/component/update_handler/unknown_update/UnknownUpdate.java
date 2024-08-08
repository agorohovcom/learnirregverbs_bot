//package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update;
//
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
//import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update.strategy.UnknownUpdateStrategy;
//import lombok.Getter;
//import org.telegram.telegrambots.meta.api.objects.Update;
//
//@Getter
//public class UnknownUpdate extends UpdateHandler {
//    
//    private final String updateType = "Unknown";
//
//    public UnknownUpdate(Update update, String botToken, String botOwner) {
//        updateHandlerFieldsInitializer(update, updateType, botToken, botOwner);
//        choiseStrategy();
//    }
//
//    private void choiseStrategy() {
//        processingStrategy = new UnknownUpdateStrategy(this);
//    }
//}
