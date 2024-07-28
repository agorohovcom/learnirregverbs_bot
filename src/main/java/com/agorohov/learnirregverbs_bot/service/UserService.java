package com.agorohov.learnirregverbs_bot.service;

import com.agorohov.learnirregverbs_bot.entity.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    void save(User user);
}
