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

    /**
     * При запуске без профиля будет запускаться использоваться .env.dev
     */
    private static void envConfigure() {
        String profile = System.getProperty("spring.profiles.active");
        String envFileName = ".env";

        if (profile != null) {
            envFileName += "." + profile;
        } else {
            envFileName += ".dev";
        }

        Dotenv dotenv = Dotenv
                .configure()
                .filename(envFileName)
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }
}
