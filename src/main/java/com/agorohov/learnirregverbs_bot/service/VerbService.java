package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.entity.Verb;
import java.util.Optional;

public interface VerbService {
    Optional<Verb> findById(Integer id);
}
