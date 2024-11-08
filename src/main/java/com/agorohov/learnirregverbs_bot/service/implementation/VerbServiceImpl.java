package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.component.mapper.EntityDTOMapper;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.exception.VerbNotFoundByIdException;
import com.agorohov.learnirregverbs_bot.repository.VerbRepository;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerbServiceImpl implements VerbService {

    private final VerbRepository verbRepository;
    private final EntityDTOMapper mapper;
    private final Random random;

    @Override
    public VerbDTO findById(Integer id) {
        return mapper.toDTO((verbRepository.findById(id))
                .orElseThrow(() -> new VerbNotFoundByIdException("Глагол с переданым id не найден, id: " + id))
        );
    }

    @Override
    public Integer getCount() {
        return (int) verbRepository.count();
    }

    @Override
    public VerbDTO getRandomVerbDTO() {
        return findById(random.nextInt(getCount()) + 1);
    }
}
