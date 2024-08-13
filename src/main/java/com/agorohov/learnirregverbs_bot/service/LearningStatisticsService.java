package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;

public interface LearningStatisticsService {
    void save(LearningStatisticsDTO learningStatistics);
    boolean existByUserChatIdAndVerbId(Long userChatId, Integer verbId);
    LearningStatisticsDTO getByUserChatIdAndVerbId(Long userChatId, Integer verbId);
}
