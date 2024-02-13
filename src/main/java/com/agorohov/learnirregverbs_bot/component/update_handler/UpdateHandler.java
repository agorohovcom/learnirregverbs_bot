package com.agorohov.learnirregverbs_bot.component.update_handler;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.api.objects.Update;

@Configuration
@Data
@PropertySource("application.yaml")
public abstract class UpdateHandler {
    
    private UpdateProcessingStrategy processingStrategy;
    
    public void doWork() {
        processingStrategy.processUpdate();
    }
    
    @Value("${bot.owner}")
    protected String botOwner;
}
