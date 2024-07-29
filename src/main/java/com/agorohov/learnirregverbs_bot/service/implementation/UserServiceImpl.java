package com.agorohov.learnirregverbs_bot.service.implementation;

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

    @Override
    public Optional<UserDTO> findById(Long id) {
        var userEntityOptional = userRepository.findById(id);
        return userEntityOptional.isPresent()
                ? Optional.of(convertEntityToDTO(userEntityOptional.get()))
                : Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

//    @Override
//    public void save(UserDTO dto) {
//        UserEntity entity = convertDTOtoEntity(dto);
//        // если такого пользователя ещё нет, устанавливаем id и
//        // время первого сообщения
//        if (findById(dto.getChatId()).isEmpty()) {
//            entity
//                    .setChatId(dto.getChatId())
//                    .setFirstMessageAt(dto.getFirstMessageAt());
//        }
//        // и в любом случае обновляем имя (вдруг изменилось) и
//        // время последнего сообщения
//        entity
//                .setUserName(dto.getUserName())
//                .setLastMessageAt(dto.getLastMessageAt());
//
//        try {
//            userRepository.save(entity);
//            log.info("User with id = " + entity.getChatId() + " was saved to DB");
//        } catch (DataAccessException e) {
//            log.error(e.getMessage());
//        }
//    }
    @Override
    public void save(UserDTO dto) {
        UserEntity entity = existsById(dto.getChatId())
                ? convertDTOtoEntity(findById(dto.getChatId()).get())
                        .setUserName(dto.getUserName())
                        .setLastMessageAt(dto.getLastMessageAt())
                : new UserEntity()
                        .setChatId(dto.getChatId())
                        .setUserName(dto.getUserName())
                        .setFirstMessageAt(dto.getLastMessageAt())
                        .setLastMessageAt(dto.getLastMessageAt());

        try {
            userRepository.save(entity);
            log.info("User with id = " + entity.getChatId() + " was saved to DB");
        } catch (DataAccessException e) {
            log.error(e.getMessage());
        }
    }

    private UserEntity convertDTOtoEntity(UserDTO dto) {
        return new UserEntity()
                .setChatId(dto.getChatId())
                .setUserName(dto.getUserName())
                .setFirstMessageAt(dto.getFirstMessageAt())
                .setLastMessageAt(dto.getLastMessageAt());
    }

    private UserDTO convertEntityToDTO(UserEntity entity) {
        return new UserDTO()
                .setChatId(entity.getChatId())
                .setUserName(entity.getUserName())
                .setFirstMessageAt(entity.getFirstMessageAt())
                .setLastMessageAt(entity.getLastMessageAt());
    }
}
