package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import java.util.List;

public interface LearningStatisticsService {

    void save(LearningStatisticsDTO learningStatistics);
    
    void saveWin(LearningStatisticsDTO learningStatistics);
    
    void saveLose(LearningStatisticsDTO learningStatistics);

    boolean existByUserChatIdAndVerbId(Long userChatId, Integer verbId);

    LearningStatisticsDTO findByUserChatIdAndVerbId(Long userChatId, Integer verbId);
    
    List<LearningStatisticsDTO> getAllStatisticsById(Long userChatId);
    
    void deleteAllByUserChatId(Long userChatId);
}
