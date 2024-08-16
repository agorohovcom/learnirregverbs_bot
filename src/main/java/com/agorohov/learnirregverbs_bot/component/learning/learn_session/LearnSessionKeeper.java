package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Map;
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

    private final VerbService verbService;
    private final LearningStatisticsService learningStatisticsService;

    // может сделать из проперти доставать?
    private static final Integer VERBS_IN_SESSION = 3;
    private static final Integer CYCLES_IN_SESSION = 2;

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

        VerbDTO[] verbs = new VerbDTO[VERBS_IN_SESSION];
        // пока просто рандом
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!   надо поменять на !!!!!!!!!!!!!!!!!!!!!
        // ЧУДО-ЮДО АЛГОРИТМ по рангам
        verbs = Stream.generate(() -> verbService.getRandomVerbDTO())
                .distinct()
                .limit(verbs.length)
                .toArray(VerbDTO[]::new);

        LearningStatisticsDTO[] stats = new LearningStatisticsDTO[VERBS_IN_SESSION];
        for (int i = 0; i < VERBS_IN_SESSION; i++) {
            stats[i] = learningStatisticsService.existByUserChatIdAndVerbId(userId, verbs[i].getId())
                    ? learningStatisticsService.getByUserChatIdAndVerbId(userId, verbs[i].getId())
                    : new LearningStatisticsDTO()
                            .setUser(new UserDTO()
                                    .setChatId(userId))
                            .setVerb(verbs[i])
                            .setRank((short) 0)
                            .setAttempts(0)
                            .setCorrectSeries(0);
        }

        LearnSession result = new LearnSession(userId, verbs, stats, CYCLES_IN_SESSION);

        log.info("User (id = " + userId + ") received a new batch of verbs");

        return put(result);
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
                //                .filter(e -> ((now - e.getValue().getCreatedAt()) < 10000)) // 10 сек для теста
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        log.info(sizeBefore - learnSessions.size()
                + " old learn sessions cleared, "
                + learnSessions.size()
                + " sessions left");
    }
}
