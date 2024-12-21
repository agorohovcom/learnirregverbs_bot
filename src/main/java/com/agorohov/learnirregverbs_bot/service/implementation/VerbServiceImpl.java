package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.component.mapper.EntityDTOMapper;
import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.exception.VerbNotFoundByIdException;
import com.agorohov.learnirregverbs_bot.repository.VerbRepository;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerbServiceImpl implements VerbService {

    private final VerbRepository verbRepository;
    private final EntityDTOMapper mapper;
    private final Random random;

    // todo поменять на что-то другое
//    private final ApplicationContext applicationContext;

    @Override
    public VerbDTO findById(Integer id) {
//        System.out.println("В кэше нет глагола с id " + id);
//        log.info("В кэше нет глагола с id {}", id);
//        System.out.println("Текущий размер кэша: " + cacheService.getCacheSize("verbs"));
        return mapper.toDTO((verbRepository.findById(id))
                .orElseThrow(() -> new VerbNotFoundByIdException("Глагол с переданным id не найден, id: " + id))
        );
    }

    @Override
    public Integer getCount() {
        return (int) verbRepository.count();
    }

//    @Override
//    public VerbDTO getRandomVerbDTO() {
//        VerbService verbService = applicationContext.getBean(VerbService.class);
//        return verbService.findById(random.nextInt(getCount()) + 1);
//    }
}
