package com.agorohov.learnirregverbs_bot.repository;

import com.agorohov.learnirregverbs_bot.entity.LearningStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningStatisticsRepository extends JpaRepository<LearningStatistics, Long>{
    
}
