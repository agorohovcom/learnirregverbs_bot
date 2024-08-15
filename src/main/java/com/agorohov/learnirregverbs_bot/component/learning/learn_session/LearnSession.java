package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import lombok.Data;

@Data
public class LearnSession {

    private final long userId;
    private final VerbDTO[] verbs;
    private final int cycles;
    private final long createdAt = System.currentTimeMillis();

    // нужна переменная, которая будет указывать на текущий глагол до завершения теста
    // по завершению теста нереключить глагол
    private int pos = 0;

    // нужен метод getVerb(), который выдаёт глагол исходя из переменной выше
    public VerbDTO getVerb() {
        VerbDTO verb = verbs[pos];
        return verb;
    }

    public VerbDTO getNextVerb() {
        pos++;
        answersReceived = 0;
        isThreeAnswersReceived = false;
        return getVerb();
    }

    private int answersReceived = 0;
    private String[] answers = new String[3];

    private boolean isThreeAnswersReceived;

    public void saveAnswer(String answer) {
        if (answersReceived < 3) {
            answers[answersReceived++] = answer
                    .replace("/learn_test_ok_infinitive_", "")
                    .replace("/learn_test_ok_past_", "")
                    .replace("/learn_test_ok_pastparticiple_", "")
                    .replace("/learn_test_fail_", "");
        }
        if (answersReceived == 3) {
            isThreeAnswersReceived = true;
        }
    }

    public boolean isCorrectResult() {
        return answers[0].equals(getVerb().getInfinitive())
                && answers[1].equals(getVerb().getPast())
                && answers[2].equals(getVerb().getPastParticiple());
    }
}
