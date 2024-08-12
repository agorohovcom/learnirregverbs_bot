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

    public TestButtons create(VerbDTO verb) {
        Map<Integer, String[]> buttons = new HashMap<>();

        Integer[] rightButtonIndexes = Stream
                .generate(() -> random.nextInt(12))
                .distinct()
                .limit(3)
                .toArray(Integer[]::new);
        
        System.out.println(Arrays.toString(rightButtonIndexes));

        buttons.put(rightButtonIndexes[0], new String[]{verb.getInfinitive(), "/learn_test_ok_infinitive"});
        buttons.put(rightButtonIndexes[1], new String[]{verb.getPast(), "/learn_test_ok_past"});
        buttons.put(rightButtonIndexes[2], new String[]{verb.getPastParticiple(), "/learn_test_ok_past_participle"});
        
        for(int i = 0; i < 12; i++) {
            if(buttons.containsKey(i)) {
                continue;
            }
            buttons.put(i, new String[]{"pitooh", "/learn_test_fail_" + i});
        }

        return new TestButtons(buttons);
    }
}
