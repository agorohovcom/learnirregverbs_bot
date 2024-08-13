package com.agorohov.learnirregverbs_bot.component.learning.test_buttons;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestButtonsBuilder {

    private final VerbService verbService;
    private final Random random;

    private static final int BUTTONS_AMOUNT = 12;

    public TestButtons create(VerbDTO verb) {
        Map<Integer, String[]> buttons = new HashMap<>();

        // находим случайные индексы для кнопок с верными ответами
        Integer[] rightButtonIndexes = Stream
                .generate(() -> random.nextInt(BUTTONS_AMOUNT))
                .distinct()
                .limit(3)
                .toArray(Integer[]::new);

        buttons.put(rightButtonIndexes[0], new String[]{verb.getInfinitive(), "/learn_test_ok_" + verb.getInfinitive()});
        buttons.put(rightButtonIndexes[1], new String[]{verb.getPast(), "/learn_test_ok_past" + verb.getPast()});
        buttons.put(rightButtonIndexes[2], new String[]{verb.getPastParticiple(), "/learn_test_ok_" + verb.getPastParticiple()});

        // получим рандомные неправильные ответы
        String[] randomFailVerbsForms = getRandomVerbs(verb, BUTTONS_AMOUNT);

        for (int i = 0; i < BUTTONS_AMOUNT; i++) {
            if (buttons.containsKey(i)) {
                continue;
            }
            buttons.put(i, new String[]{randomFailVerbsForms[i], "/learn_test_fail_" + randomFailVerbsForms[i]});
        }

        return new TestButtons(buttons);
    }

    private String[] getRandomVerbs(VerbDTO verb, int amount) {
        // получим массив из рандомных форм глаголов amount штук
        String[] result = Stream
                .generate(() -> verbService.getRandomVerbDTO())
                .filter(e -> !e.equals(verb))
                .distinct()
                .limit(amount)
                .map(e -> e.getRandomFormByIndex(random.nextInt(2)))
                .toArray(String[]::new);

        return result;
    }
}
