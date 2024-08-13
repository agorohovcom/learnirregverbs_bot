package com.agorohov.learnirregverbs_bot.dto;

import com.agorohov.learnirregverbs_bot.exception.VerbFormNotFoundByIndexException;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VerbDTO {

    private Integer id;
    private String infinitive;
    private String past;
    private String pastParticiple;
    private String translation;

    @Override
    public String toString() {
        return infinitive
                + " / " + past
                + " / " + pastParticiple;
    }

    public String getRandomFormByIndex(int formNumber) {
        return switch (formNumber) {
            case 0 -> infinitive;
            case 1 -> past;
            case 2 -> pastParticiple;
            default -> throw new VerbFormNotFoundByIndexException(
                    "Некорректный id: " + formNumber + ", допустимые значения 0-2");
        };
    }
}
