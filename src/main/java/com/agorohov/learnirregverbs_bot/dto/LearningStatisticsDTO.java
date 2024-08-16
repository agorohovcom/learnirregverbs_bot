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

    // функционал перенесён в сервис
//    public LearningStatisticsDTO wins() {
//        if (attempts == null) {
//            attempts = 0;
//        }
//        if (correctSeries == null) {
//            correctSeries = 0;
//        }
//        attempts++;
//        correctSeries++;
//        rankUp();
//        return this;
//    }

//    public LearningStatisticsDTO loses() {
//        if (attempts == null) {
//            attempts = 0;
//        }
//        attempts++;
//        correctSeries = 0;
//        rankDown();
//        return this;
//    }

//    private void rankUp() {
//        if (rank == null) {
//            rank = 0;
//        }
//        if (correctSeries >= 10) {
//            rank = 6;
//            return;
//        }
//        if (rank < 5) {
//            rank++;
//        }
//    }

//    private void rankDown() {
//        if (rank == null) {
//            rank = 0;
//        }
//        if (correctSeries >= 10) {
//            rank = 6;
//            return;
//        }
//        if (rank > 0) {
//            rank--;
//        }
//    }
}
