//package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;
//
//import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
//import lombok.RequiredArgsConstructor;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//import org.telegram.telegrambots.meta.api.objects.Update;
//
//@RequiredArgsConstructor
//public class StartTextUpdateStrategy implements UpdateProcessingStrategy {
//
//    private final Update update;
//
//    @Override
//    public BotApiMethod processUpdate() {
//        String textToSend = "𝕃𝕖𝕒𝕣𝕟 𝕀𝕣𝕣𝕖𝕘𝕦𝕝𝕒𝕣 𝕍𝕖𝕣𝕓𝕤 𝔹𝕠𝕥\n\n"
//                + "Привет, " + update.getMessage().getFrom().getFirstName() + "!\n\n"
//                + "Это бот для изучения неправильных глаголов английского языка.\n\n"
//                + "Ты можешь учиться и следить за прогрессом своего обучения.\n\n"
//                + "Выбери необходимый раздел:";
//
//        var sendMessage = MessageBuilder
//                .create()
//                .setChatId(update.getMessage().getFrom().getId())
//                .setText(textToSend)
//                .row()
//                .button("Учить неправильные глаголы", "/learn")
//                .endRow()
//                .row()
//                .button("Статистика", "/stat")
//                .endRow()
//                .row()
//                .button("О боте", "/about")
//                .endRow()
//                .row()
//                .button("Помощь", "/help")
//                .endRow();
//        
//        return updateOrCreateMessage(update, sendMessage);
//    }
//}
