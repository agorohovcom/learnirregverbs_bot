package com.agorohov.learnirregverbs_bot.config;

import java.util.Random;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@Data
@PropertySource("application.yaml")
@EnableScheduling
//@EnableAsync
//@ConfigurationProperties(prefix = "bot")
public class BotConfig {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.owner}")
    private String botOwner;

    @Bean
    public Random random() {
        return new Random();
    }
}
