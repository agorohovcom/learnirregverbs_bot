package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.entity.LearningStatisticsEntity;
import com.agorohov.learnirregverbs_bot.entity.UserEntity;
import com.agorohov.learnirregverbs_bot.entity.VerbEntity;
import com.agorohov.learnirregverbs_bot.repository.LearningStatisticsRepository;
import com.agorohov.learnirregverbs_bot.service.LearningStatisticsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningStatisticsServiceImpl implements LearningStatisticsService {

    private final LearningStatisticsRepository learningStatisticsRepository;

    @Transactional
    @Override
    public void save(LearningStatisticsDTO learningStatistics) {
        learningStatisticsRepository.saveAndFlush(convertDTOtoEntity(learningStatistics));
    }

    @Override
    public boolean existByUserChatIdAndVerbId(Long userChatId, Integer verbId) {
        return learningStatisticsRepository.existsByUserChatIdAndVerbId(userChatId, verbId);
    }

    @Override
    public LearningStatisticsDTO getByUserChatIdAndVerbId(Long userChatId, Integer verbId) {
        return convertEntityToDTO(learningStatisticsRepository.findByUserChatIdAndVerbId(userChatId, verbId));
    }

    private LearningStatisticsEntity convertDTOtoEntity(LearningStatisticsDTO dto) {
        return new LearningStatisticsEntity()
                .setId(dto.getId())
                .setAttempts(dto.getAttempts())
                .setCorrectSeries(dto.getCorrectSeries())
                .setRank(dto.getRank())
                .setUser(new UserEntity()
                        .setChatId(dto.getUser().getChatId())
                        .setUserName(dto.getUser().getUserFirstName())
                        .setFirstMessageAt(dto.getUser().getFirstMessageAt())
                        .setLastMessageAt(dto.getUser().getLastMessageAt()))
                .setVerb(new VerbEntity()
                        .setId(dto.getVerb().getId())
                        .setInfinitive(dto.getVerb().getInfinitive())
                        .setPast(dto.getVerb().getPast())
                        .setPastParticiple(dto.getVerb().getPastParticiple()));
    }

    private LearningStatisticsDTO convertEntityToDTO(LearningStatisticsEntity entity) {
        return new LearningStatisticsDTO()
                .setId(entity.getId())
                .setAttempts(entity.getAttempts())
                .setCorrectSeries(entity.getCorrectSeries())
                .setRank(entity.getRank())
                .setUser(new UserDTO()
                        .setChatId(entity.getUser().getChatId())
                        .setUserFirstName(entity.getUser().getUserName())
                        .setFirstMessageAt(entity.getUser().getFirstMessageAt())
                        .setLastMessageAt(entity.getUser().getLastMessageAt()))
                .setVerb(new VerbDTO()
                        .setId(entity.getVerb().getId())
                        .setInfinitive(entity.getVerb().getInfinitive())
                        .setPast(entity.getVerb().getPast())
                        .setPastParticiple(entity.getVerb().getPastParticiple()));
    }
}
