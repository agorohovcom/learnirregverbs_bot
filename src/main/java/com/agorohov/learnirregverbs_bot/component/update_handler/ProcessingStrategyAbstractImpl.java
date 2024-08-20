package com.agorohov.learnirregverbs_bot.component.update_handler;

import com.agorohov.learnirregverbs_bot.utils.MessageBuilder;
import jakarta.annotation.PostConstruct;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public abstract class ProcessingStrategyAbstractImpl implements ProcessingStrategy {

    protected String strategyName;
    
    @PostConstruct
    public void init() {
        strategyName = this.getClass().getSimpleName();
    }

    protected abstract MessageBuilder strategyRealization(UpdateWrapper wrapper);

    public final BotApiMethod processUpdate(UpdateWrapper wrapper) {
        wrapper.setStrategy(strategyName);
        var sendMessage = strategyRealization(wrapper);
        return updateOrCreateMessage(wrapper, sendMessage);
    }
}
