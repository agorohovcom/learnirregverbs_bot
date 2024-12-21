package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import org.springframework.cache.annotation.Cacheable;

public interface VerbService {

    @Cacheable("verbs")
    VerbDTO findById(Integer id);

    @Cacheable("verbCount")
    Integer getCount();
}
