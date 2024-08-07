package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.entity.VerbEntity;
import java.util.Optional;

public interface VerbService {
    Optional<VerbEntity> findById(Integer id);
}
