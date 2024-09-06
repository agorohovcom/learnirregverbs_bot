package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LearnSessionKeeper {

    private final VerbService verbService;

    @Value("${session.verbs_amount}")
    private int verbs_amount;
    @Value("${session.cycles_amount}")
    private int cycles_amount;

    private Map<Long, LearnSession> learnSessions = new ConcurrentHashMap<>();

    public LearnSession put(LearnSession session) {
        learnSessions.put(session.getUserId(), session);
        return session;
    }

    public LearnSession get(Long userId) {
        return learnSessions.get(userId);
    }

    public boolean isExists(Long userId) {
        return learnSessions.containsKey(userId);
    }

    public boolean hasNextVerb(Long userId) {
        return get(userId).hasNextVerb();
    }

    public LearnSession createAndPutAndGet(Long userId) {
        VerbDTO[] verbs = getRandomVerbDtos();
//        VerbDTO[] verbs = getRandomVerbDtosByRank();
//        VerbDTO[] verbs = getAlwaysFirstFiveVerbDtos(5);
        
        LearnSession result = new LearnSession(userId, verbs, cycles_amount);

        log.info("User (id = " + userId + ") received a new batch of verbs");

        return put(result);
    }
    
    // verbs_amount случайных глаголов из БД
    private VerbDTO[] getRandomVerbDtos() {
        return Stream.generate(() -> verbService.getRandomVerbDTO())
                .distinct()
                .limit(verbs_amount)
                .toArray(VerbDTO[]::new);
    }
    
    // verbs_amount случайных глаголов из БД учитывая ранг (больше мелких рангов)
    private VerbDTO[] getRandomVerbDtosByRank() {
        VerbDTO[] result = new VerbDTO[verbs_amount];
        
        for(int i = 0; i < verbs_amount; i++) {
            VerbDTO verb = verbService.getRandomVerbDTO();
            // вот тут высчитываем с помощью random учитывая ранг берем ли этот глагол
            // если нет - получаем следующее, и так пока не наберём verbs_amount штук
        }
        
        return result;
    }
    
    // всегда первые amount глаголов для теста
    private VerbDTO[] getAlwaysFirstFiveVerbDtos(int amount) {
        VerbDTO[] result = new VerbDTO[5];
        
        for (int i = 0; i < 5; i++) {
            result[i] = verbService.findById(i + 1);
            
        }
        
        return result;
    }

    @Async
    @Scheduled(cron = "${cron.cleanOldLearnSessions}", zone = "Europe/Moscow")
    private void cleanOldLearnSessions() {
        long now = System.currentTimeMillis();
        long sizeBefore = learnSessions.size();

        learnSessions = learnSessions
                .entrySet()
                .stream()
                .filter(e -> ((now - e.getValue().getCreatedAt()) < 7200000))
                //                                .filter(e -> ((now - e.getValue().getCreatedAt()) < 5000)) // 5 сек для теста
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        log.info(sizeBefore - learnSessions.size()
                + " old learn sessions cleared, "
                + learnSessions.size()
                + " sessions left");
    }
}
