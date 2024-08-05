package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
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
    protected int msgId;
    protected String msgBody;
    protected String msgCallbackData;
    
    protected Integer updateId;

    protected boolean isAdmin;

    protected long updateWasReceivedAt;

    protected String updateType;

    protected UpdateProcessingStrategy processingStrategy;

    // сомнительноооооо, ннно окэй (я про возвращаемый тип)
    public BotApiMethod doWork() {
        log.info("Update was recived ("
                + "id = "
                + updateId
                + ", type = "
                + updateType
                + ", strategy = "
                + processingStrategy.getClass().getSimpleName()
                + ").");
        return processingStrategy.processUpdate();
    }

    protected void updateHandlerFieldsInitializer(Update update, String updateType, String botOwner) {
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
