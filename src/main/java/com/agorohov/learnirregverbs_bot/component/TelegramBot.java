package com.agorohov.learnirregverbs_bot.component;

import com.agorohov.learnirregverbs_bot.component.update_handler.*;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_updates.*;
import com.agorohov.learnirregverbs_bot.config.BotConfig;
import com.agorohov.learnirregverbs_bot.entity.User;
import com.agorohov.learnirregverbs_bot.service.UserService;
import java.sql.Timestamp;
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
//@EnableScheduling
@PropertySource("application.yaml")
public class TelegramBot extends TelegramLongPollingBot implements BotCommands {

    @Autowired
    private UserService userService;

    private final BotConfig config;
    private final Long botStartTime;

    public TelegramBot(BotConfig config) {
        botStartTime = System.currentTimeMillis();
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
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

    @Override
    public void onUpdateReceived(Update update) {

        /**
         * Нужно продумать структуру для обработки апдейтов: 
         * 1. ✔ Приходит апдейт 
         * 2. - Обрабатывается классом для работы с таблицами 
         * 3. ✔ Устанавливаем тип апдейта - текст или коллбэкдата (в противном случае null) 
         * 4. ✔ Начинается ли апдейт с "/admin" (для каждого сообщение отдельная реализация поведения) 
         * 5. ✔ ... хз, я пошел в зал, сегодня спина и трицепсы
         */
        
        // updateHandler получает ссылку на TextUpdate или CallbackQueryUpdate, смотся какой update,
        // далее в конструкторе выбирает реализацию UpdateProcessingStrategy
        UpdateHandler updateHandler = new UpdateTypeDistributor().distribute(update);

        // продумываю работу с БД (потом убрать в отдельный класс!!!)
        // ...

        // update обрабатывается согласно установленному поведению
        updateHandler.doWork();

        /** ниже тестовый код, здоровается на любой апдейт и добавляет юзера в БД
        if (update.hasMessage()) {
            
            // фиксируем время
            var updateTime = new Timestamp(System.currentTimeMillis());

            log.debug("Update was recived (updateId=" + update.getUpdateId() + ").");

            // получаем необходимые данные из сообщения
            var userMessage = update.getMessage();
            var chat = userMessage.getChat();
            long chatId = userMessage.getChatId();
            String userName = chat.getUserName();

            User user = new User()
                    .setChatId(chatId)
                    .setUserName(userName)
                    .setLastMessageAt(updateTime);

            // есть ли такой юзер
//            if (userService.findById(chatId).isEmpty()) {}
            userService.save(user);

            String textToSend = "Привет, " + userName + "!";

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(chatId));
            sendMessage.setText(textToSend);

            try {
                execute(sendMessage);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }*/
    }
}
