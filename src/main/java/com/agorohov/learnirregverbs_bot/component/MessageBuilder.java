package com.agorohov.learnirregverbs_bot.component;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Data
@Accessors(chain = true)
public class MessageBuilder {

    private String text;
    private long chatId;
    private int messageId;

    private List<List<InlineKeyboardButton>> underMessageKeyboard = new ArrayList<>();
    private List<InlineKeyboardButton> row = null;

    private MessageBuilder() {
    }

    public static MessageBuilder create() {
        return new MessageBuilder();
    }

    public static MessageBuilder create(long chatId) {
        return new MessageBuilder().setChatId(chatId);
    }

    public MessageBuilder row() {
        this.row = new ArrayList<>();
        return this;
    }

    public MessageBuilder button(String buttonText, String callBackData) {
        var button = new InlineKeyboardButton();
        button.setText(buttonText);
        button.setCallbackData(callBackData);
        row.add(button);
        return this;
    }

    public MessageBuilder endRow() {
        this.underMessageKeyboard.add(this.row);
        this.row = null;
        return this;
    }

    public SendMessage buildNewMessage() {
        SendMessage message = new SendMessage();

        message.setChatId(chatId);
        message.setText(text);

        var keyboardMarkup = new InlineKeyboardMarkup();

        keyboardMarkup.setKeyboard(underMessageKeyboard);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

    public EditMessageText buildUpdateMessage() {
        EditMessageText message = new EditMessageText();

        message.setChatId(chatId);
        message.setText(text);
        message.setMessageId(messageId);
        
        var keyboardMarkup = new InlineKeyboardMarkup();

        keyboardMarkup.setKeyboard(underMessageKeyboard);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }
}
