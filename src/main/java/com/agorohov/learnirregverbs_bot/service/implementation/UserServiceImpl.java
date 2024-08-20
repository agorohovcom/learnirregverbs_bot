package com.agorohov.learnirregverbs_bot.service.implementation;

import com.agorohov.learnirregverbs_bot.component.mapper.EntityDTOMapper;
import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import com.agorohov.learnirregverbs_bot.entity.UserEntity;
import com.agorohov.learnirregverbs_bot.repository.UserRepository;
import com.agorohov.learnirregverbs_bot.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EntityDTOMapper mapper;

    @Override
    public Optional<UserDTO> findById(Long id) {
        var userEntityOptional = userRepository.findById(id);
        return userEntityOptional.isPresent()
                ? Optional.of(mapper.toDTO(userEntityOptional.get()))
                : Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

//    @Transactional
    @Override
    public void save(UserDTO dto) {
        boolean isUserExists = existsById(dto.getChatId());

        // если пользователь с таким ID уже есть, используем его,
        // только обновляем имя (вдруг изменилось) и дату последнего сообщения.
        // если пользователя с таким ID ещё нет, создаём нового
        UserEntity entity = isUserExists
                ? mapper.toEntity(findById(dto.getChatId()).get())
                        .setUserName(dto.getUserFirstName())
                        .setLastMessageAt(dto.getLastMessageAt())
                : mapper.toEntity(dto);

        try {
            userRepository.saveAndFlush(entity);
            if (!isUserExists) {
                log.info("New user with id = " + entity.getChatId() + " added to the DB");
            }
        } catch (DataAccessException e) {
            log.error(e.getMessage());
        }
    }
}
