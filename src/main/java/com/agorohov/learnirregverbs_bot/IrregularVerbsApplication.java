package com.agorohov.learnirregverbs_bot;

import com.agorohov.learnirregverbs_bot.utils.DotenvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IrregularVerbsApplication {

    public static void main(String[] args) {
        DotenvLoader.loadEnvironmentVariables();
        SpringApplication.run(IrregularVerbsApplication.class, args);
    }
}
