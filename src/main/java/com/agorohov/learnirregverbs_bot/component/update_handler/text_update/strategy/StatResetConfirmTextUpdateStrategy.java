//package com.agorohov.learnirregverbs_bot.component.update_handler.text_update.strategy;
//
//import com.agorohov.learnirregverbs_bot.component.MessageBuilder;
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
//import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateProcessingStrategy;
//import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
//
//public class StatResetConfirmTextUpdateStrategy implements UpdateProcessingStrategy {
//
//    private final UpdateHandler uh;
//
//    public StatResetConfirmTextUpdateStrategy(UpdateHandler uh) {
//        this.uh = uh;
//    }
//
//    @Override
//    public BotApiMethod processUpdate() {
//        String textToSend = "ℝ𝕖𝕤𝕖𝕥 𝕤𝕥𝕒𝕥𝕚𝕔𝕥𝕚𝕔𝕤\n\n"
//                + uh.getUserFirstName() + ", твоя статистика изучения "
//                + "неправильных глаголов удалена.\n\n"
//                + "Но ты можешь начать заново!";
//
//        var sendMessage = MessageBuilder
//                .create()
//                .setChatId(uh.getUserId())
//                .setText(textToSend)
//                .row()
//                .button("<< главное меню", "/start")
//                .endRow();
//        
//        return updateOrCreateMessage(uh, sendMessage);
//    }
//}
