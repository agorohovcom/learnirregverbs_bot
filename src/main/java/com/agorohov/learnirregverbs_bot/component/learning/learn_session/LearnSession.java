package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import java.util.Arrays;
import lombok.Data;

@Data
public class LearnSession {

    private final long userId;
    private final VerbDTO verb;
    private final long createdAt;

    private int answersReceived = 0;
    private String[] answers = new String[3];

    private boolean isAllAnswersReceived;

    public void saveAnswer(String answer) {
        if (answersReceived < 3) {
            answers[answersReceived++] = answer
                    .replace("/learn_test_ok_infinitive_", "")
                    .replace("/learn_test_ok_past_", "")
                    .replace("/learn_test_ok_pastparticiple_", "")
                    .replace("/learn_test_fail_", "");
        }
        if (answersReceived == 3) {
            isAllAnswersReceived = true;
        }
    }

    public boolean isCorrectResult() {
//        return answers[0].endsWith(verb.getInfinitive())
//                && answers[1].endsWith(verb.getPast())
//                && answers[2].endsWith(verb.getPastParticiple());
        return answers[0].equals(verb.getInfinitive())
                && answers[1].equals(verb.getPast())
                && answers[2].equals(verb.getPastParticiple());
    }
}
