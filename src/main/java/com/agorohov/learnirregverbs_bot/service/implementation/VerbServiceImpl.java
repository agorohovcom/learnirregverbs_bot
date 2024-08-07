package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.entity.VerbEntity;
import com.agorohov.learnirregverbs_bot.repository.VerbRepository;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerbServiceImpl implements VerbService {
    
    private final VerbRepository verbRepository;
    
    @Override
    public Optional<VerbEntity> findById(Integer id){
        return verbRepository.findById(id);
    }
}