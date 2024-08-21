package com.agorohov.learnirregverbs_bot.component;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandlerFactory;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
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

    private final UpdateHandlerFactory updateHandlerFactory;
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
        
        // Добавляю к Update дополнительные данные с помощью класса-обертки
        UpdateWrapper wrapper = new UpdateWrapper(
                update,
                System.currentTimeMillis(),
                isItBotId(getIdFromUpdate(update)),
                botStartsAt
        );
        
//        System.out.println("wrapper.getMessage().getChatId() = " + wrapper.getMessage().getChatId());;
//        System.out.println("Integer.valueOf(getBotOwner() = " + Integer.valueOf(getBotOwner()));
        
        if(wrapper.getMessage().getChatId().equals(Long.valueOf(getBotOwner()))) {
            wrapper.setAdmin(true);
        }
        
//        System.out.println("Bot Owner: " + getBotOwner());
//        System.out.println("Id From Update: " + getIdFromUpdate(update));
//        System.out.println("Is Admin: " + wrapper.isAdmin());

        userService.save(wrapper.giveMeUserDTO());

        var updateProcessingResult = updateHandlerFactory.getHandler(wrapper).handle(wrapper);

        try {
            if (wrapper.isExecutable()) {
                execute(updateProcessingResult);
            }
        } catch (TelegramApiException e) {
            if (e.getMessage().contains("message is not modified")) {
                log.warn("Message is not modified, user (id = " + wrapper.getMessage().getChatId() + ") presses the buttons too quickly");
            } else {
                log.error(e.getMessage());
            }
        } finally {
            log.info("Update received ("
                    + "userId = "
                    + wrapper.getMessage().getChatId()
                    + ", updateId = "
                    + wrapper.getUpdate().getUpdateId()
                    + ", type = "
                    + wrapper.getType()
                    + ", strategy = "
                    + wrapper.getStrategy()
                    + ")");
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

    public String getBotOwner() {
        return config.getBotOwner();
    }

    private boolean isItBotId(Long botId) {
        return getBotToken().split(":")[0].equals(String.valueOf(botId));
    }

//    private boolean isAdmin(long id) {
//        return getBotOwner().equals(String.valueOf(id));
//    }

    // Этот метод показывает id пользователя только при текстовых сообщениях,
    // при CallbackData показывает id бота
    private long getIdFromUpdate(Update update) {
        // было так, ругалось когда я редактировал своё сообщение,
        // потому что это другой тип апдейта
        // пока оставлю так, дальше надо сделать красиво
//        return update.hasMessage()
//                ? update.getMessage().getFrom().getId()
//                : update.getCallbackQuery().getMessage().getFrom().getId();

        long result = 0;
        if (update.hasMessage()) {
            result = update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            result = update.getCallbackQuery().getMessage().getFrom().getId();
        }
        return result;
    }
}
