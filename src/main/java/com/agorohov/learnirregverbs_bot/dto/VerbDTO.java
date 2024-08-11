package com.agorohov.learnirregverbs_bot.dto;

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
    public String toString(){
        return infinitive
                + " / " + past
                + " / " + pastParticiple
                + " (" + translation
                + ")";
    }
}
