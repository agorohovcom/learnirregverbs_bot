package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Slf4j
public abstract class UpdateHandler {

    protected Message message;
    protected long userId;
    protected String userName;
    protected String userFirstName;
    protected Integer msgId;
    protected String msgBody;
    protected String msgCallbackData;

    protected Integer updateId;

    protected boolean isAdmin;
    protected long updateWasReceivedAt;
    protected String updateType;

    protected UpdateProcessingStrategy processingStrategy;

    protected String botToken;

    public boolean isUpdatable(String newTextToSend) {
        // проверяем что сообщение от бота,
        // что прошло менее 47 часов (вообще максимум 48)
        // и что сообщение имеет другое содержимое (убрал эту проверку)
        return ((message.getFrom().getId().toString().equals(botToken))
                && ((System.currentTimeMillis() - updateWasReceivedAt) < 47 * 3600000))
                && (!msgBody.equals(newTextToSend));
    }

    // сомнительноооооо, ннно окэй (я про возвращаемый тип)
    public BotApiMethod doWork() {
        return processingStrategy.processUpdate();
    }

    protected void updateHandlerFieldsInitializer(
            Update update,
            String updateType,
            String botToken,
            String botOwner) {
        message = update.hasMessage()
                ? update.getMessage()
                : update.getCallbackQuery().getMessage();

        userId = message.getChatId();
        userName = message.getChat().getUserName();
        userFirstName = message.getChat().getFirstName();
        msgId = message.getMessageId();
        msgBody = message.getText();

        updateId = update.getUpdateId();

        msgCallbackData = update.hasCallbackQuery()
                ? update.getCallbackQuery().getData()
                : "";

        this.updateType = updateType;
        this.botToken = botToken;
//        System.out.println("botToken: " + botToken);

        isAdmin = botOwner.equals(String.valueOf(userId));

        updateWasReceivedAt = System.currentTimeMillis();
    }

    public UserDTO giveMeUserDTO() {
        return new UserDTO()
                .setChatId(userId)
                .setUserName(userName)
                .setLastMessageAt(new Timestamp(updateWasReceivedAt));
    }

//        public void printInfo(){
//        System.out.println();
//        System.out.println("getSimpleName() : " + this.getClass().getSimpleName());
//        System.out.println("getMessage().toString() : " + this.getMessage().toString());
//        System.out.println("getUserId() : " + this.getUserId());
//        System.out.println("getUserName() : " + this.getUserName());
//        System.out.println("getUserFirstName() : " + this.getUserFirstName());
//        System.out.println("getMsgId() : " + this.getMsgId());
//        System.out.println("getMsgBody() : " + this.getMsgBody());
//        System.out.println("getMsgCallbackData() : " + this.getMsgCallbackData());
//        System.out.println("isAdmin() : " + this.isAdmin());
//        System.out.println("getProcessingStrategy() : " + this.getProcessingStrategy());
//        System.out.println();
//    }
}
