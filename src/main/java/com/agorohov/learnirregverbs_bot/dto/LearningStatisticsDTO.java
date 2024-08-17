package com.agorohov.learnirregverbs_bot.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LearningStatisticsDTO {

    private Long id;
    private Integer attempts;
    private Integer correctSeries;
    private Short rank;

    private VerbDTO verb;
    private UserDTO user;
}
