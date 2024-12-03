package com.agorohov.learnirregverbs_bot.repository;

import com.agorohov.learnirregverbs_bot.entity.LearningStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LearningStatisticsRepository extends JpaRepository<LearningStatisticsEntity, Long> {

    @Override
    LearningStatisticsEntity saveAndFlush(LearningStatisticsEntity learningStatistics);
    
//    void deleteByUserChatId(Long userChatId);        // так работало норм
    
    @Modifying
    @Query(value = "DELETE FROM learning_statistics WHERE user_chat_id = :user_chat_id", nativeQuery = true)
    void deleteByUserChatId(@Param("user_chat_id") Long userChatId);
    
    @Query(value = "SELECT * FROM learning_statistics WHERE user_chat_id = :user_chat_id", nativeQuery = true)
    List<LearningStatisticsEntity> findAllStatisticsByUserChatId(@Param("user_chat_id") Long userChatId);

    @Query(value = "SELECT * FROM learning_statistics WHERE user_chat_id = :user_chat_id AND verb_id = :verb_id", nativeQuery = true)
    Optional<LearningStatisticsEntity> findByUserChatIdAndVerbId(@Param("user_chat_id") Long userChatId, @Param("verb_id") Integer verbId);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM learning_statistics WHERE user_chat_id = :user_chat_id AND verb_id = :verb_id)", nativeQuery = true)
    boolean existsByUserChatIdAndVerbId(@Param("user_chat_id") Long userChatId, @Param("verb_id") Integer verbId);
}
