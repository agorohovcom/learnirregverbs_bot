package com.agorohov.learnirregverbs_bot.component;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "главное меню"),
            new BotCommand("/learn", "учить неправильные глаголы"),
            new BotCommand("/stat", "твоя статистика"),
            new BotCommand("/about", "информация о боте"),
            new BotCommand("/help", "помощь")
    );
}