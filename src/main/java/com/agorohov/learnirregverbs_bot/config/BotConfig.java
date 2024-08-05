package com.agorohov.learnirregverbs_bot.config;

import com.agorohov.learnirregverbs_bot.component.update_handler.UpdateHandler;
import com.agorohov.learnirregverbs_bot.component.update_handler.callbackquery_update.CallbackQueryUpdate;
import com.agorohov.learnirregverbs_bot.component.update_handler.text_update.TextUpdate;
import com.agorohov.learnirregverbs_bot.component.update_handler.unknown_update.UnknownUpdate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.api.objects.Update;

@Configuration
@Data
@PropertySource("application.yaml")
//@ConfigurationProperties(prefix = "bot")
public class BotConfig {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.owner}")
    private String botOwner;
}