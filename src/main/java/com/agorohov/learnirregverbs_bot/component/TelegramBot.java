package com.agorohov.learnirregverbs_bot.component;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandlerFactory;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.config.BotConfig;
import com.agorohov.learnirregverbs_bot.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot implements BotCommands {

    private final UpdateHandlerFactory updateHandlerFactory;
    private final BotConfig config;
    private final UserService userService;

    private long botStartsAt;

    public TelegramBot(UpdateHandlerFactory updateHandlerFactory, BotConfig config, UserService userService) {
        super(config.getBotToken());
        this.updateHandlerFactory = updateHandlerFactory;
        this.config = config;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: {}", e.getMessage());
        }
        botStartsAt = System.currentTimeMillis();
    }

    @Override
    public void onUpdateReceived(Update update) {

        UpdateWrapper wrapper = new UpdateWrapper(
                update,
                System.currentTimeMillis(),
                botStartsAt
        );
        
        if(wrapper.getMessage().getChatId().equals(Long.valueOf(getBotOwner()))) {
            wrapper.setAdmin(true);
        }

        userService.save(wrapper.giveMeUserDTO());

        BotApiMethod<? extends Serializable> updateProcessingResult = updateHandlerFactory.getHandler(wrapper).handle(wrapper);

        try {
            if (wrapper.isExecutable()) {
                execute(updateProcessingResult);
            }
        } catch (TelegramApiException e) {
            if (e.getMessage().contains("message is not modified")) {
                log.warn("Message is not modified, user (id = {}) presses the buttons too quickly",
                        wrapper.getMessage().getChatId());
            } else {
                log.error(e.getMessage());
            }
        } finally {
            log.info("Update received (userId = {}, updateId = {}, type = {}, strategy = {})",
                    wrapper.getMessage().getChatId(),
                    wrapper.getUpdate().getUpdateId(),
                    wrapper.getType(),
                    wrapper.getStrategy());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    public String getBotOwner() {
        return config.getBotOwner();
    }
}
