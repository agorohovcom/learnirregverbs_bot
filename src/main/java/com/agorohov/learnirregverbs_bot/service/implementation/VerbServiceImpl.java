package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.component.mapper.EntityDTOMapper;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.exception.VerbNotFoundByIdException;
import com.agorohov.learnirregverbs_bot.repository.VerbRepository;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerbServiceImpl implements VerbService {

    private final VerbRepository verbRepository;
    private final EntityDTOMapper mapper;
    private final Random random;
    private final ApplicationContext applicationContext;

    @Override
    @Cacheable("verbs")
    public VerbDTO findById(Integer id) {
        System.out.println("В кэше нет глагола с id " + id);
        return mapper.toDTO((verbRepository.findById(id))
                .orElseThrow(() -> new VerbNotFoundByIdException("Глагол с переданным id не найден, id: " + id))
        );
    }

    @Override
    public Integer getCount() {
        return (int) verbRepository.count();
    }

    @Override
    public VerbDTO getRandomVerbDTO() {
        VerbService verbService = applicationContext.getBean(VerbService.class);
        return verbService.findById(random.nextInt(getCount()) + 1);
    }
}
