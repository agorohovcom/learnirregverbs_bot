package com.agorohov.learnirregverbs_bot.component;

import com.agorohov.learnirregverbs_bot.component.update_handler.*;
import com.agorohov.learnirregverbs_bot.config.BotConfig;
import com.agorohov.learnirregverbs_bot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@PropertySource("application.yaml")
public class TelegramBot extends TelegramLongPollingBot implements BotCommands {

    @Autowired
    private UserService userService;

    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        // updateHandler получает ссылку на TextUpdate или другой класс, смотря какой тип update,
        // далее UpdateTypeDistributor выбирает реализацию UpdateProcessingStrategy,
        // с помощью метода isUpdateFromAdmin() устанавливаем значение поля isAdmin
        UpdateHandler updateHandler = UpdateTypeDistributor.distribute(update, isUpdateFromAdmin(update));

        log.info("Update was recived (type = "
                + updateHandler.getUpdateType()
                + ", id = "
                + update.getUpdateId()
                + ", strategy = " + updateHandler.getProcessingStrategy().getClass().getSimpleName()
                + ").");

        // продумываю работу с БД (потом убрать в отдельный класс!!!)
//        User user = new User()
//                    .setChatId(update.getMessage().getChatId())
//                    .setUserName(update.getMessage().getChat().getUserName())
//                    .setLastMessageAt(new Timestamp(System.currentTimeMillis()));
//        try{
//            userService.save(user);
//        }catch(DataAccessException e){
//            log.error(e.getMessage());
//        }
        // update обрабатывается согласно установленной стратегии
        try {
            execute(updateHandler.doWork());
        } catch (TelegramApiException ex) {
            log.error(ex.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    // проверяем, от админа Update или нет
    private boolean isUpdateFromAdmin(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId().toString().equals(config.getBotOwner());
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId().toString().equals(config.getBotOwner());
        } else {
            return false;
        }
    }

}
