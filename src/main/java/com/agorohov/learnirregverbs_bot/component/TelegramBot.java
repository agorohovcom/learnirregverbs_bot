package com.agorohov.learnirregverbs_bot.component;

import com.agorohov.learnirregverbs_bot.component.update_handler.*;
import com.agorohov.learnirregverbs_bot.config.BotConfig;
import com.agorohov.learnirregverbs_bot.service.UserService;
import lombok.extern.slf4j.Slf4j;
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

//    @Autowired
    private UserService userService;

    private final BotConfig config;

    public TelegramBot(BotConfig config, UserService userService) {
        this.userService = userService;
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        // updateHandler получает ссылку на TextUpdate или CallbackQueryUpdate, смотря какой update,
        // далее в конструкторе выбирается реализация UpdateProcessingStrategy
        UpdateHandler updateHandler = UpdateTypeDistributor.distribute(update);
        
        log.info(updateHandler.getUpdateType() + " update was recived (updateId=" + update.getUpdateId() + ").");

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
        // ...
        
        // update обрабатывается согласно установленной стратегии и в виде
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

}