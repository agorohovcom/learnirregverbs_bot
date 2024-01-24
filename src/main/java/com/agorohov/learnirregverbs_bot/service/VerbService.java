package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.entity.Verb;
import com.agorohov.learnirregverbs_bot.repository.VerbRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerbService {
    
    private final VerbRepository verbRepository;
    
    public Optional<Verb> findById(Long id){
        return verbRepository.findById(id);
    }
}