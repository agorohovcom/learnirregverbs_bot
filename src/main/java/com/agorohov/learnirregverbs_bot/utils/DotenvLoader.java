package com.agorohov.learnirregverbs_bot.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvLoader {

    /**
     * Если профиль не указан при запуске приложения, будет использоваться профиль dev по умолчанию.
     * Переменные из файла .env будут переопределены переменными из файла для активного профиля, если таковые имеются.
     */
    public static void loadEnvironmentVariables() {
        String profile = System.getProperty("spring.profiles.active");
        String envFileName = ".env";

        // Загрузка базового файла .env
        Dotenv dotenv = Dotenv.configure()
                .filename(envFileName)
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();

        // Загрузка переменных из базового файла
        dotenv.entries().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            System.setProperty(key, value);
        });


        // Определение файла для активного профиля или использование dev по умолчанию
        String profileEnvFileName = profile != null
                ? "%s.%s".formatted(envFileName, profile)
                : "%s.dev".formatted(envFileName);

        // Загрузка файла для активного профиля
        Dotenv profileDotenv = Dotenv.configure()
                .filename(profileEnvFileName)
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();

        // Загрузка переменных из файла профиля
        profileDotenv.entries().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            System.setProperty(key, value);
        });
    }
}
