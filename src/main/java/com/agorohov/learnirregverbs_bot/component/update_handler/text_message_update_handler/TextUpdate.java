package com.agorohov.learnirregverbs_bot.component.update_handler.text_message_update_handler;

//package com.agorohov.learnirregverbs_bot.component.update_handler.text_update;
//
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
//import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy.*;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.telegram.telegrambots.meta.api.objects.Update;
//
//@Getter
//@RequiredArgsConstructor
//public class TextUpdate extends UpdateHandler {
//    
//public final String updateType = "Text";
//
//    public TextUpdate(Update update, String botToken, String botOwner) {
//        updateHandlerFieldsInitializer(update, updateType, botToken, botOwner);
//        choiseStrategy();
//    }
//
//    private void choiseStrategy() {
//        switch (msgBody) {
//            case "/start" ->
//                processingStrategy = new StartTextUpdateStrategy(this);
//            case "/admin" -> {
//                if (isAdmin) {
//                    processingStrategy = new AdminTextUpdateStrategy(this);
//                } else {
//                    processingStrategy = new DefaultTextUpdateStrategy(this);
//                }
//            }
//            case "/learn" ->
//                processingStrategy = new LearnTextUpdateStrategy(this);
//            case "/stat" ->
//                processingStrategy = new StatTextUpdateStrategy(this);
//            case "/about" ->
//                processingStrategy = new AboutTextUpdateStrategy(this);
//            case "/help" ->
//                processingStrategy = new HelpTextUpdateStrategy(this);
//            default ->
//                processingStrategy = new DefaultTextUpdateStrategy(this);
//        }
//    }
//}
