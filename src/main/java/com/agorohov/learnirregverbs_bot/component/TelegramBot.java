package com.agorohov.learnirregverbs_bot.component;

import com.agorohov.learnirregverbs_bot.component.update_handler.*;
import com.agorohov.learnirregverbs_bot.config.BotConfig;
import com.agorohov.learnirregverbs_bot.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot implements BotCommands {

    private final BotConfig config;
    private final UserService userService;

    private long botStartsAt;

    @PostConstruct
    private void init() {
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
        botStartsAt = System.currentTimeMillis();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // updateHandler получает ссылку на TextUpdate или другой класс, смотря какой тип update,
        // далее UpdateTypeDistributor выбирает реализацию UpdateProcessingStrategy,
        // передаем botOwner, чтобы сравнить с id пользователя и понять, от админа ли update
        UpdateHandler updateHandler = UpdateTypeDistributor.distribute(update, config.getBotOwner());

//        updateHandler.printInfo();

        log.info("Update was recived ("
                + "id = "
                + update.getUpdateId()
                + ", type = "
                + updateHandler.getUpdateType()
                + ", strategy = "
                + updateHandler.getProcessingStrategy().getClass().getSimpleName()
                + ").");

        // обновляем даныне о пользователе в БД или добавляем нового
        userService.save(updateHandler.giveMeUserDTO());
        
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
}