package com.agorohov.learnirregverbs_bot.component;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandlerFactory;
import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateWrapper;
import com.agorohov.learnirregverbs_bot.config.BotConfig;
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
                isAdmin(getIdFromUpdate(update))
        );

        try {
            execute(updateHandlerFactory.getHandler(wrapper).handle(wrapper));
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
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
        return botId.equals(getBotToken().split(":")[0]);
    }
    
    private boolean isAdmin(long id){
        return getBotOwner().equals(String.valueOf(id));
    }

    private long getIdFromUpdate(Update update) {
        return update.hasMessage()
                ? update.getMessage().getFrom().getId()
                : update.getCallbackQuery().getMessage().getFrom().getId();
    }
}
