package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LearnSessionKeeper {
    private Map<Long, LearnSession> learnSessions = new ConcurrentHashMap<>();
    
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
    
    // удаляет сессии, которым 2 часа и больше
    @Async
    @Scheduled(cron = "${cron.cleanOldLearnSessions}", zone = "Europe/Moscow")
    private void cleanOldLearnSessions() {
        long now = System.currentTimeMillis();
        long sizeBefore = learnSessions.size();
        
        learnSessions = learnSessions
                .entrySet()
                .stream()
                .filter(e -> ((now - e.getValue().getCreatedAt()) < 7200000))
//                .filter(e -> ((now - e.getValue().getCreatedAt()) < 10000))   // 10 сек для теста
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                
        log.info(sizeBefore - learnSessions.size()
                + " old learn sessions cleared, "
                + learnSessions.size()
                + " sessions left.");
    }
}
