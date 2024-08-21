package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.dto.UserDTO;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findById(Long id);
    boolean existsById(Long id);
    void save(UserDTO user);
    Integer getCount();
}