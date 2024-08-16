package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import lombok.Data;

@Data
public class LearnSession {

    private final long userId;
    private final VerbDTO[] verbs;
    private final int cycles;
    private final long createdAt = System.currentTimeMillis();

    private Integer step;
    
    private String[] answers = new String[3];
    private int answersReceived = 0;
    private boolean isThreeAnswersReceived;
    
    public boolean hasNextVerb() {
        if (step == null) {
            step = 0;
        }
        return step + 1 < verbs.length * cycles;
    }
    
    public boolean hasVerb(){
        if (step == null) {
            step = 0;
        }
        return step < verbs.length * cycles;
    }

    private int getVerbIndex() {
        return step % verbs.length;
    }

    // выдаёт текущий глагол
    public VerbDTO getVerb() {
        if (step == null) {
            step = 0;
        }
        return verbs[getVerbIndex()];
    }

    public VerbDTO getNextVerb() {
        if (step == null) {
            step = 0;
        } else {
            step++;
        }
        answersReceived = 0;
        isThreeAnswersReceived = false;
        return getVerb();
    }

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
