package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.component.mapper.EntityDTOMapper;
import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.entity.LearningStatisticsEntity;
import com.agorohov.learnirregverbs_bot.repository.LearningStatisticsRepository;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LearningStatisticsServiceImpl implements LearningStatisticsService {

    private final LearningStatisticsRepository learningStatisticsRepository;
    private final EntityDTOMapper mapper;

    //    @Transactional      // ???
    @Override
    public void save(LearningStatisticsDTO learningStatistics) {
        boolean isStatExists = existByUserChatIdAndVerbId(
                learningStatistics.getUser().getChatId(),
                learningStatistics.getVerb().getId());

        LearningStatisticsEntity entity = isStatExists
                ? mapper.toEntity(findByUserChatIdAndVerbId(learningStatistics.getUser().getChatId(),
                        learningStatistics.getVerb().getId())
                        .setAttempts(learningStatistics.getAttempts()))
                .setCorrectSeries(learningStatistics.getCorrectSeries())
                .setRank(learningStatistics.getRank())
                : mapper.toEntity(learningStatistics);

        learningStatisticsRepository.saveAndFlush(entity);

        log.info("Test result for user (id = {}) and verb (id = {}) saved to the DB",
                learningStatistics.getUser().getChatId(),
                learningStatistics.getVerb().getId());
    }

    @Override
    public void saveWin(LearningStatisticsDTO learningStatistics) {
        if (learningStatistics.getAttempts() == null) {
            learningStatistics.setAttempts(0);
        }
        if (learningStatistics.getCorrectSeries() == null) {
            learningStatistics.setCorrectSeries(0);
        }
        learningStatistics.setAttempts(learningStatistics.getAttempts() + 1);
        learningStatistics.setCorrectSeries(learningStatistics.getCorrectSeries() + 1);
        if (learningStatistics.getRank() == null) {
            learningStatistics.setRank((short) 0);
        }
        if (learningStatistics.getCorrectSeries() >= 10) {
            learningStatistics.setRank((short) 6);
        }
        if (learningStatistics.getRank() < 5) {
            learningStatistics.setRank((short) (learningStatistics.getRank() + 1));
        }
        save(learningStatistics);
    }

    @Override
    public void saveLose(LearningStatisticsDTO learningStatistics) {
        if (learningStatistics.getAttempts() == null) {
            learningStatistics.setAttempts(0);
        }
        learningStatistics.setAttempts(learningStatistics.getAttempts() + 1);
        learningStatistics.setCorrectSeries(0);
        if (learningStatistics.getRank() == null) {
            learningStatistics.setRank((short) 0);
        }
        if (learningStatistics.getCorrectSeries() >= 10) {
            learningStatistics.setRank((short) 6);
        }
        if (learningStatistics.getRank() > 0) {
            learningStatistics.setRank((short) (learningStatistics.getRank() - 1));
        }
        save(learningStatistics);
    }

    @Override
    public boolean existByUserChatIdAndVerbId(Long userChatId, Integer verbId) {
        return learningStatisticsRepository.existsByUserChatIdAndVerbId(userChatId, verbId);
    }

    @Override
    public LearningStatisticsDTO findByUserChatIdAndVerbId(Long userChatId, Integer verbId) {
        return mapper.toDTO(learningStatisticsRepository.findByUserChatIdAndVerbId(userChatId, verbId).get());
    }

    @Override
    public List<LearningStatisticsDTO> getAllStatisticsById(Long userChatId) {
        return learningStatisticsRepository.findAllStatisticsByUserChatId(userChatId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional  // ???
    @Override
    public void deleteAllByUserChatId(Long userChatId) {
        learningStatisticsRepository.deleteByUserChatId(userChatId);
        log.info("The user (id = {}) has reset their statistics", userChatId);
    }
}
