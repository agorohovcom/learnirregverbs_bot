package com.agorohov.learnirregverbs_bot.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvLoader {

    /**
     * Если профиль не указан при запуске приложения, будет использоваться профиль dev по умолчанию.
     * Переменные из файла .env будут переопределены переменными из файла для активного профиля, если таковые имеются.
     */
    public static void envConfigure() {
        String profile = System.getProperty("spring.profiles.active");
        String envFileName = ".env";

        // Загрузка базового файла .env
        Dotenv dotenv = Dotenv.configure()
                .filename(envFileName)
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();

        // Определение файла для активного профиля или использование dev по умолчанию
        String profileEnvFileName = profile != null
                ? envFileName + "." + profile
                : envFileName + ".dev";

        // Загрузка файла для активного профиля
        Dotenv profileDotenv = Dotenv.configure()
                .filename(profileEnvFileName)
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();

        // Переопределение переменных из базового файла
        profileDotenv.entries().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            System.setProperty(key, value);
        });

        // Добавление переменных окружения в системные свойства
        dotenv.entries().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            if(System.getProperty(key) == null) {
                System.setProperty(key, value);
            }
        });
    }
}
