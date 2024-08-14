package com.agorohov.learnirregverbs_bot.component.mapper;

import com.agorohov.learnirregverbs_bot.dto.LearningStatisticsDTO;
import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.entity.LearningStatisticsEntity;
import com.agorohov.learnirregverbs_bot.entity.UserEntity;
import com.agorohov.learnirregverbs_bot.entity.VerbEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOMapper {
    
    public UserDTO toDTO(UserEntity entity) {
        return new UserDTO()
                .setChatId(entity.getChatId())
                .setUserFirstName(entity.getUserName())
                .setFirstMessageAt(entity.getFirstMessageAt())
                .setLastMessageAt(entity.getLastMessageAt());
    }
    
    public UserEntity toEntity(UserDTO dto) {
        return new UserEntity()
                .setChatId(dto.getChatId())
                .setUserName(dto.getUserFirstName())
                .setFirstMessageAt(dto.getFirstMessageAt())
                .setLastMessageAt(dto.getLastMessageAt());
    }
    
    public VerbDTO toDTO(VerbEntity entity) {
        return new VerbDTO()
                .setId(entity.getId())
                .setInfinitive(entity.getInfinitive())
                .setPast(entity.getPast())
                .setPastParticiple(entity.getPastParticiple())
                .setTranslation(entity.getTranslation());
    }
    
    public LearningStatisticsDTO toDTO(LearningStatisticsEntity entity) {
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
    
    public LearningStatisticsEntity toEntity(LearningStatisticsDTO dto) {
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
}
