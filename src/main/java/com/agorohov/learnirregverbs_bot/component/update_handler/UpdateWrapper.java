package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import java.sql.Timestamp;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

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

    public Message getMessage() {
        Message result = null;
        if (update.hasMessage()) {
            result = update.getMessage();
        }
        if (update.hasCallbackQuery()) {
            result = (Message) update.getCallbackQuery().getMessage();
        }
        if (update.hasEditedMessage()) {
            result = update.getEditedMessage();
        }
        if (update.hasChannelPost()) {
            result = update.getChannelPost();
        }
        if (update.hasEditedChannelPost()) {
            result = update.getEditedChannelPost();
        }
        return result;
    }

    public UserDTO giveMeUserDTO() {
        return new UserDTO()
                .setChatId(getMessage().getChatId())
                .setUserFirstName(getMessage().getChat().getUserName())
                .setLastMessageAt(new Timestamp(updateWasReceivedAt));
    }
}
