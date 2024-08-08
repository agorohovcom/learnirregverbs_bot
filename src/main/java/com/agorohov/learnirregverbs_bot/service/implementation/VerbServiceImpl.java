package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.dto.VerbDTO;
import com.agorohov.learnirregverbs_bot.entity.VerbEntity;
import com.agorohov.learnirregverbs_bot.exception.VerbNotFoundByIdException;
import com.agorohov.learnirregverbs_bot.repository.VerbRepository;
import com.agorohov.learnirregverbs_bot.service.VerbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerbServiceImpl implements VerbService {

    private final VerbRepository verbRepository;

    @Override
    public VerbDTO findById(Integer id) {
        return convertEntityToDTO((verbRepository.findById(id))
                .orElseThrow(() -> new VerbNotFoundByIdException("Глагол с переданым id не найден"))
        );
    }

    @Override
    public Integer getCount() {
        return (int) verbRepository.count();
    }

    // это вроде как не должно потребоваться, если че удалю
//    private VerbEntity convertDTOtoEntity(VerbDTO dto) {
//        return new VerbEntity()
//                .setId(dto.getId())
//                .setInfinitive(dto.getInfinitive())
//                .setPast(dto.getPast())
//                .setPastParticiple(dto.getPastParticiple())
//                .setTranslation(dto.getTranslation());
//    }
    private VerbDTO convertEntityToDTO(VerbEntity entity) {
        return new VerbDTO()
                .setId(entity.getId())
                .setInfinitive(entity.getInfinitive())
                .setPast(entity.getPast())
                .setPastParticiple(entity.getPastParticiple())
                .setTranslation(entity.getTranslation());
    }
}
