package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.repository.LearningStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningStatisticsService {
    
    private final LearningStatisticsRepository learningStatisticsRepository;
}
