package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import lombok.Data;

@Data
public class LearnSession {
    private final long userId;
    private final VerbDTO verb;
    private final long createdAt;
}
