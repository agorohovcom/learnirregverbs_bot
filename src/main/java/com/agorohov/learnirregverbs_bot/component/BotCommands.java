package com.agorohov.learnirregverbs_bot.component;

import java.util.List;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "главное меню"),
            new BotCommand("/learn", "учить неправильные глаголы"),
            new BotCommand("/stat", "твоя статистика"),
            new BotCommand("/about", "о боте"),
            new BotCommand("/help", "помощь"),
            new BotCommand("/feedback", "оставить обратную связь"),
            new BotCommand("/admin", "это для админа :)")
    );
}