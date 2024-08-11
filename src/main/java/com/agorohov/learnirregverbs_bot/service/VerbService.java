package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;

public interface VerbService {

    VerbDTO findById(Integer id);

    Integer getCount();
    
    VerbDTO getRandomVerbDTO();
}
