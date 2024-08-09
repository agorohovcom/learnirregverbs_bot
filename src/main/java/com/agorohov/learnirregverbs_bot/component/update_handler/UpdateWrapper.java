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
    private final boolean isFromBot;
    private final boolean isAdmin;
    
    // для логгирования
    private String type;
    private String strategy;

    public Message getMessage() {
        return update.hasMessage()
                ? update.getMessage()
                : update.getCallbackQuery().getMessage();
    }
    
    public UserDTO giveMeUserDTO() {
        return new UserDTO()
                .setChatId(getMessage().getChatId())
                .setUserFirstName(getMessage().getChat().getUserName())
                .setLastMessageAt(new Timestamp(updateWasReceivedAt));
    }
}
