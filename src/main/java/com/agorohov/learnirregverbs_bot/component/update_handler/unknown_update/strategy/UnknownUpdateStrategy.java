//package com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update.strategy;
//
//import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//
//public class UnknownUpdateStrategy implements UpdateProcessingStrategy {
//
//    private final UpdateHandler uh;
//
//    public UnknownUpdateStrategy(UpdateHandler uh) {
//        this.uh = uh;
//    }
//
//    @Override
//    public BotApiMethod processUpdate() {
//        String textToSend = "𝕆𝕠𝕡𝕤\n\n"
//                + "Такой тип сообщений не поддерживается.\n"
//                + "Используй кнопки или меню бота.\n\n"
//                + "Если нужна помощь, загляни в раздел \"Помощь\"";
//
//        var sendMessage = MessageBuilder
//                .create()
//                .setChatId(uh.getUserId())
//                .setText(textToSend)
//                .row()
//                .button("< помощь", "/help")
//                .endRow()
//                .row()
//                .button("<< главное меню", "/start")
//                .endRow();
//        
//        return updateOrCreateMessage(uh, sendMessage);
//    }
//}
