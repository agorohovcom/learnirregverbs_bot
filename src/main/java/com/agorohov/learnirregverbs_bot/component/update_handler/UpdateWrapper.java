package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.MaybeInaccessibleMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Setter
public class UpdateWrapper {
    private final Update update;
    private final long updateWasReceivedAt;
    private final long botStartsAt;     // это для чертогов админа

    private final Message supportedMessageOrNull;
    private final boolean isAdmin;

    // для логирования
    private String type;
    private String strategy;

    // вызывать ли метод execute
    private boolean isExecutable = true;

    public UpdateWrapper(Update update, long updateWasReceivedAt, long botStartsAt, String botOwner) {
        this.update = update;
        this.updateWasReceivedAt = updateWasReceivedAt;
        this.botStartsAt = botStartsAt;
        supportedMessageOrNull = tryExtractSupportedMessage();
        isAdmin = supportedMessageOrNull != null
                && supportedMessageOrNull.getChatId().equals(Long.valueOf(botOwner));
    }

    private Message tryExtractSupportedMessage() {
        if (update.hasMessage()) {
            return update.getMessage();
        }
        if (update.hasCallbackQuery()) {
            MaybeInaccessibleMessage message = update.getCallbackQuery().getMessage();
            if (message instanceof Message) {
                return (Message) update.getCallbackQuery().getMessage();
            }
        }
        if (update.hasEditedMessage()) {
            return update.getEditedMessage();
        }
        if (update.hasChannelPost()) {
            return update.getChannelPost();
        }
        if (update.hasEditedChannelPost()) {
            return update.getEditedChannelPost();
        }
        return null;
    }

    public Optional<UserDTO> giveMeUserDTO() {
        Optional<UserDTO> result = Optional.empty();
        if (supportedMessageOrNull != null) {
            UserDTO userDTO = new UserDTO()
                    .setChatId(supportedMessageOrNull.getChatId())
                    .setUserFirstName(supportedMessageOrNull.getChat().getUserName())
                    .setLastMessageAt(new Timestamp(updateWasReceivedAt));
            result = Optional.of(userDTO);
        }
        return result;
    }
}
