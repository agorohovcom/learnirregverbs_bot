package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;
import java.util.Optional;

@Data
public class UpdateWrapper {

    private final Update update;
    private final long updateWasReceivedAt;
    private final long botStartsAt;

    // для логирования
    private String type;
    private String strategy;
    
    // админ?
    private boolean isAdmin;

    // вызывать ли метод execute
    private boolean isExecutable = true;

    // todo надо реализовать обработку случаев когда метод возвращает null
    // это может произойти в update.hasCallbackQuery() или в этих случаях:
    // hasPoll()
    // hasPollAnswer()
    // hasInlineQuery()
    // hasChosenInlineQuery()
    // Думаю, можно либо возвращать Optional, либо добавить везде перед использованием метода getMessage
    // проверку на null. Если null - ничего не делать, как-то так.
    public Optional<Message> getMessage() {
        Optional<Message> result = Optional.empty();
        if (update.hasMessage()) {
            result = Optional.of(update.getMessage());
        }
        if (update.hasCallbackQuery()) {
            var message = update.getCallbackQuery().getMessage();
            if (message instanceof Message) {
                result = Optional.of((Message) update.getCallbackQuery().getMessage());
            }
        }
        if (update.hasEditedMessage()) {
            result = Optional.of(update.getEditedMessage());
        }
        if (update.hasChannelPost()) {
            result = Optional.of(update.getChannelPost());
        }
        if (update.hasEditedChannelPost()) {
            result = Optional.of(update.getEditedChannelPost());
        }
        return result;
    }

    public Optional<UserDTO> giveMeUserDTO() {
        Optional<Message> message = getMessage();
        return new UserDTO()
                .setChatId(getMessage().getChatId())
                .setUserFirstName(getMessage().getChat().getUserName())
                .setLastMessageAt(new Timestamp(updateWasReceivedAt));
    }
}
