package com.agorohov.learnirregverbs_bot.dto;

import java.sql.Timestamp;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {
    private Long chatId;
    private String userName;
    private Timestamp firstMessageAt;
    private Timestamp lastMessageAt;
}
