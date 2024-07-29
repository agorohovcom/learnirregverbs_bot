package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.repository.LearningStatisticsRepository;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningStatisticsServiceImpl implements LearningStatisticsService {
    
    private final LearningStatisticsRepository learningStatisticsRepository;
}
