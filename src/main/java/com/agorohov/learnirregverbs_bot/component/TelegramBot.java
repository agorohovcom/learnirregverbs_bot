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
//        System.out.println(getIdFromUpdate(update));
//        System.out.println(getIdFromUpdate2(update));

        // Добавляю к Update дополнительные данные с помощью класса-обертки
        UpdateWrapper wrapper = new UpdateWrapper(
                update,
                System.currentTimeMillis(),
                isItBotId(getIdFromUpdate(update)),
                isAdmin(getIdFromUpdate(update))
        );

        userService.save(wrapper.giveMeUserDTO());

        var updateProcessingResult = updateHandlerFactory.getHandler(wrapper).handle(wrapper);

        try {
            if (wrapper.isExecutable()) {
                execute(updateProcessingResult);
            }
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
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
            
            // Надо ли нулить? Чтобы быстрее GC отработал
//            wrapper = null;
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

    private boolean isAdmin(long id) {
        return getBotOwner().equals(String.valueOf(id));
    }

    private long getIdFromUpdate(Update update) {
//        update.getEditedMessage().getFrom().getId();
        return update.hasMessage()
                ? update.getMessage().getFrom().getId()
                : update.getCallbackQuery().getMessage().getFrom().getId();
    }

//    private long getIdFromUpdate2(Update update) {
//        return update.hasMessage()
//                ? update.getMessage().getFrom().getId()
//                : update.getCallbackQuery().getMessage().getChatId();
//    }
}
