package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.config.BotConfig;
import com.agorohov.learnirregverbs_bot.entity.User;
import java.sql.Timestamp;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
//@EnableScheduling
@PropertySource("application.yaml")
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private UserService userService;
    
    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
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
        if (update.hasMessage() /**
                 * && update.getMessage().hasText()
                 */
                ) {

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
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

}
