package com.agorohov.learnirregverbs_bot.component.learning;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class LearningWithTestSession {
    
    private final VerbService verbService;
        
    private final long userId;
    private final VerbDTO[] verbs;
}