//package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;
//
//import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//import org.telegram.telegrambots.meta.api.objects.Update;
//
//@Component
//public class StatTextUpdateStrategy implements UpdateProcessingStrategy {
//    
////    private final UpdateHandler uh;
////
////    public StatTextUpdateStrategy(UpdateHandler uh) {
////        this.uh = uh;
////    }
//
//    @Override
//    public BotApiMethod processUpdate(Update update) {
//        String textToSend = "𝕊𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
//                + update.getMessage().getFrom().getFirstName() + ", а вот и твоя статистика.\n"
//                + "Тут вкратце объяснено по статистике. Здорово, правда?";
//
//        var sendMessage = MessageBuilder
//                .create()
//                .setChatId(update.getMessage().getChatId())
//                .setText(textToSend)
//                .row()
//                .button("Обнулить статистику", "/stat_reset")
//                .endRow()
//                .row()
//                .button("< учить", "/learn")
//                .endRow()
//                .row()
//                .button("<< главное меню", "/start")
//                .endRow();
//        
//        return sendMessage.buildNewMessage();
//        
////        return updateOrCreateMessage(uh, sendMessage);
//    }
//}
