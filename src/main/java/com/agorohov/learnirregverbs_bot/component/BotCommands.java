package com.agorohov.learnirregverbs_bot.component;

import java.util.List;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "сообщение-приветствие"),
            new BotCommand("/buttons_test", "тест кнопок"),
//            new BotCommand("/statistics", "личная статистика"),
            new BotCommand("/help", "помощь"),
//            new BotCommand("/delete", "удалить мои данные и остановить бота"),
            new BotCommand("/admin", "сорри, это только для админа")
    );

}