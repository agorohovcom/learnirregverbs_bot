package com.agorohov.learnirregverbs_bot.component.learning.test_buttons;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class TestButtonsBuilder {

    private final VerbService verbService;
    private final Random random;

    private static final int TEST_BUTTONS_AMOUNT = 12;

    public TestButtons create(VerbDTO verb) {
        Map<Integer, String[]> buttons = new HashMap<>();

        // находим случайные индексы для кнопок с верными ответами
        // и добавляем дубль одной из форм для повышения сложности
        Integer[] rightButtonIndexes = Stream
                .generate(() -> random.nextInt(TEST_BUTTONS_AMOUNT))
                .distinct()
                .limit(4)
                .toArray(Integer[]::new);

        // определяем дубль какой формы будем добавлять
        int dubleIndex = random.nextInt(3);
//        System.out.println(dubleIndex);
        String[] forms = {verb.getInfinitive(), verb.getPast(), verb.getPastParticiple()};
        String randomDuble = forms[dubleIndex];

        buttons.put(rightButtonIndexes[0], new String[]{verb.getInfinitive(), "/learn_test_ok_infinitive_" + verb.getInfinitive()});
        buttons.put(rightButtonIndexes[1], new String[]{verb.getPast(), "/learn_test_ok_past_" + verb.getPast()});
        buttons.put(rightButtonIndexes[2], new String[]{verb.getPastParticiple(), "/learn_test_ok_pastparticiple_" + verb.getPastParticiple()});
        // добавление лишней правильной формы
        buttons.put(rightButtonIndexes[3], new String[]{randomDuble, "/learn_test_random_double_" + randomDuble});

        // получим рандомные неправильные ответы
        String[] randomFailVerbsForms = getRandomVerbs(verb);

        for (int i = 0; i < TEST_BUTTONS_AMOUNT; i++) {
            if (buttons.containsKey(i)) {
                continue;
            }
            buttons.put(i, new String[]{randomFailVerbsForms[i], "/learn_test_fail_" + randomFailVerbsForms[i]});
        }

        return new TestButtons(buttons);
    }

    private String[] getRandomVerbs(VerbDTO verb) {
        // получим массив из рандомных форм глаголов amount штук
        return Stream
                .generate(() -> verbService.findById(random.nextInt(verbService.getCount()) + 1))
                .filter(e -> !e.equals(verb))
                .distinct()
                .limit(TestButtonsBuilder.TEST_BUTTONS_AMOUNT)
                .map(e -> e.getRandomFormByIndex(random.nextInt(2)))
                .toArray(String[]::new);
    }
}
