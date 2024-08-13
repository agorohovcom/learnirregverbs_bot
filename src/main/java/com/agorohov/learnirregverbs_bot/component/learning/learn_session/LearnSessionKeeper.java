package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import java.util.HashMap;
import java.util.Map;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LearnSessionKeeper {
    private final Map<Long, LearnSession> learnSessions = new HashMap<>();
    
    public void put(LearnSession session) {
        learnSessions.put(session.getUserId(), session);
    }
    
    public LearnSession get(Long userId) {
        return learnSessions.get(userId);
    }
    
    public void remove(Long userId) {
        learnSessions.remove(userId);
    }
    
    public boolean isExist(Long userId) {
        return learnSessions.containsKey(userId);
    }
    
    @Async
    @Scheduled(cron = "${cron.cleanOldLearnSessions}", zone = "Europe/Moscow")
    private void cleanOldLearnSessions() {
        // Тут код для запуска удаления слишком старых сессий
    }
}
