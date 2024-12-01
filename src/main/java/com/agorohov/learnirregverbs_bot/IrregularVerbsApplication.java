package com.agorohov.learnirregverbs_bot;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IrregularVerbsApplication {

    public static void main(String[] args) {
        envConfigure();
        SpringApplication.run(IrregularVerbsApplication.class, args);
    }

    private static void envConfigure() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }
}
