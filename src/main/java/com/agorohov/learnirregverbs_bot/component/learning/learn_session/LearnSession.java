package com.agorohov.learnirregverbs_bot.component.learning.learn_session;

import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LearnSession {
    @Getter
    private final long userId;
    private final VerbDTO[] verbs;
    private final int cycles;
    @Getter
    private final long createdAt = System.currentTimeMillis();

    private Integer step;

    private final String[] answers = new String[3];
    private int answersReceived = 0;
    private boolean isThreeAnswersReceived;

    private LearningStatisticsDTO statistics;

    public boolean hasNextVerb() {
        if (step == null) {
            step = 0;
        }
        return step + 1 < verbs.length * cycles;
    }

    public boolean hasVerb() {
        if (step == null) {
            step = 0;
        }
        return step < verbs.length * cycles;
    }

    private int getIndex() {
        return step % verbs.length;
    }

    // выдаёт текущий глагол
    public VerbDTO getVerb() {
        if (step == null) {
            step = 0;
        }
        return verbs[getIndex()];
    }

    public LearningStatisticsDTO getLearningStatisticsOrNull() {
        if (step == null) {
            step = 0;
        }
        if (statistics != null && statistics.getVerb().getId().equals(getVerb().getId())) {
            return statistics;
        }
        statistics = null;
        return null;
    }

    public String getStarsString(Short stars) {
        return switch (stars) {
            case 0 ->
                "☆☆☆☆☆☆";
            case 1 ->
                "★☆☆☆☆☆";
            case 2 ->
                "★★☆☆☆☆";
            case 3 ->
                "★★★☆☆☆";
            case 4 ->
                "★★★★☆☆";
            case 5 ->
                "★★★★★☆";
            default ->
                "★★★★★★";
        };
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

    public boolean isThreeAnswersReceived() {
        return isThreeAnswersReceived;
    }
    
    public String getAnswer(int index) {
        if(index < 0 || index > 2) {
            throw new IllegalArgumentException();
        }
        return answers[index];
    }

    public void saveAnswer(String answer) {
        if (answersReceived < 3) {
            answers[answersReceived++] = answer
                    .replace("/learn_test_ok_infinitive_", "")
                    .replace("/learn_test_ok_past_", "")
                    .replace("/learn_test_ok_pastparticiple_", "")
                    .replace("/learn_test_random_double_", "")
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
