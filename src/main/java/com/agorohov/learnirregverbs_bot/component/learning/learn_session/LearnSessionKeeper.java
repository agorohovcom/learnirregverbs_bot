package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LearnSessionKeeper {

    private final VerbService service;

    // может сделать из проперти доставать?
    private static final Integer VERBS_IN_SESSION = 10;
    private static final Integer CYCLES_IN_SESSION = 3;

    private Map<Long, LearnSession> learnSessions = new ConcurrentHashMap<>();

    public void put(LearnSession session) {
        learnSessions.put(session.getUserId(), session);
    }

    public LearnSession getOrCreateAndPutAndGet(Long userId) {
//        return learnSessions.get(userId);
        return Optional.ofNullable(learnSessions.get(userId)).orElseGet(() -> createAndPutAndGet(userId));
    }

    public void remove(Long userId) {
        learnSessions.remove(userId);
    }

    public boolean isExist(Long userId) {
        return learnSessions.containsKey(userId);
    }

    public LearnSession createAndPutAndGet(Long userId) {
        VerbDTO[] verbs = new VerbDTO[VERBS_IN_SESSION];
        // пока просто рандом
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!   надо поменять на !!!!!!!!!!!!!!!!!!!!!
        // ЧУДО-ЮДО АЛГОРИТМ по рангам
        verbs = Stream.generate(() -> service.getRandomVerbDTO())
                .distinct()
                .limit(verbs.length)
                .toArray(VerbDTO[]::new);
        
        // из verbService тянем слова в массив
        LearnSession result = new LearnSession(userId, verbs, CYCLES_IN_SESSION);

        put(result);
        return result;
    }

// удаляет сессии, которым 2 часа и больше
// это было для одного слова, теперь надо не удалять, а pos++
// а удалять те, которые всё
// но можно и по времени добавить максимум. да, надо бы добавить
// ПРИДЕТСЯ ПЕРЕДЕЛЫВАТЬ
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
