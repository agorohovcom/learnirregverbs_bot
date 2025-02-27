package com.agorohov.learnirregverbs_bot.utils;

import com.agorohov.learnirregverbs_bot.config.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitiateUtil implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info(Constants.WELCOME_APP_MSG_TO_LOG);
    }

}
