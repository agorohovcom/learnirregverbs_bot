package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Map;
import java.util.Random;
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
    private final LearningStatisticsService learningStatisticsService;

    private final Random random;

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
//        VerbDTO[] verbs = getRandomVerbDtos();
        VerbDTO[] verbs = getRandomVerbDtosByRank(userId);
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
    private VerbDTO[] getRandomVerbDtosByRank(long userId) {
//        System.out.println("Мы в методе getRandomVerbDtosByRank()");
        VerbDTO[] result = new VerbDTO[verbs_amount];

        for (int i = 0; i < verbs_amount;) {
            VerbDTO verb = verbService.getRandomVerbDTO();
//            System.out.println("Слово: " + verb.getInfinitive());

            int rank = learningStatisticsService.existByUserChatIdAndVerbId(userId, verb.getId())
                    ? learningStatisticsService.findByUserChatIdAndVerbId(userId, verb.getId()).getRank()
                    : 0;
//            System.out.println("Ранг: " + rank);

            int randFactor;

            if (rank >= 0 && rank < 2) {
                randFactor = 7;
            } else if (rank >= 2 && rank < 5) {
                randFactor = 5;
            } else if (rank == 5) {
                randFactor = 3;
            } else {
                randFactor = 2;
            }
//            System.out.println("Рандом фактор: " + randFactor);

            int rand = random.nextInt(10);
//            System.out.println("Рандом: " + rand);

            boolean slammed = rand <= randFactor;
//            System.out.println("Поймано: " + slammed + "\n");

            if (slammed) {
                result[i] = verb;
                i++;
            }
        }

//        System.out.println("Cписок глаголов:\n");
//        for (VerbDTO v : result) {
//            System.out.println(v);
//        }
//        System.out.println();

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
