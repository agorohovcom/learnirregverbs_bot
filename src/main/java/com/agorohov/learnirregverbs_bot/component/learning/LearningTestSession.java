package com.agorohov.learnirregverbs_bot.component.learning;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class LearningTestSession {
        
    private final long userId;
    private final VerbDTO[] verbs;
}